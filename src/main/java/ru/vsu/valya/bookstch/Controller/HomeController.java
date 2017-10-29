package ru.vsu.valya.bookstch.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.valya.bookstch.db.config.DBConfig;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Controller
public class HomeController {
    private DBConfig dbConfig;

    HomeController(){
        dbConfig=new DBConfig();
        try {
            dbConfig.createConnection();
        } catch (SQLException e) {
           error();
        } catch (ClassNotFoundException e) {
            error();
        }
    }

    @RequestMapping(value = "/")
    public String home(){
        return "home";
    }

    @RequestMapping(value = "/error")
    public String error(){
        return "error";
    }

    @RequestMapping(value = "/books")
    public String books(ModelMap modelMap){
        try {
            ResultSet resultSet=dbConfig.executeQuery("select id, name, author from book");
            ArrayList<ArrayList<String>> outputList=new ArrayList<ArrayList<String>>();

            while(resultSet.next()){
                ArrayList<String> row=new ArrayList<String>();
                row.add(Integer.toString(resultSet.getInt(1)));
                row.add(resultSet.getString(2));
                row.add(resultSet.getString(3));
                outputList.add(row);
            }

            modelMap.addAttribute("books", outputList);
        } catch (SQLException e) {
            return "error";
        }
        return "books";
    }

    private void addAddrPriceCopies(ResultSet resultSet, ArrayList<String> row, int price) throws SQLException{
        row.add(resultSet.getString("address"));
        row.add(Integer.toString(price));
        row.add(Integer.toString(resultSet.getInt("copies_number")));
    }

    @RequestMapping(value = "/bookInfo")
    public String bookInfo(int id, ModelMap modelMap){
        try {
            //------------------------------------abstract book parameters-------------------------------//
            ResultSet resultSet=dbConfig.executeQuery("select * from book where id="+id);
            while(resultSet.next()){
                modelMap.addAttribute("bookName", resultSet.getString(2));
                modelMap.addAttribute("author",   resultSet.getString(3));
                modelMap.addAttribute("publisher",   resultSet.getString(4));
                modelMap.addAttribute("releaseYear",   resultSet.getDate(5));
                modelMap.addAttribute("pagesNumber",   resultSet.getInt(6));
            }

            //--------------------------chain_stores_names---------------------------------------------------//
          resultSet=dbConfig.executeQuery(
                "select distinct(name), id  from chain_store where id=any(" +
                        "select distinct(chain_store_id)  from shop        where id=any(" +
                            "select shop_id  from concrete_book_shop   where (copies_number>0) & (book_id="+id+")))" +
                             "order by id asc");
            ArrayList<ArrayList<String>> chainStoresNamesIds=new ArrayList<ArrayList<String>>();
            /*
              chstore_name | id
              chstore_name | id
              ...
             */
            while(resultSet.next()){
                ArrayList<String> row=new ArrayList<String>();
                row.add(resultSet.getString(1));
                row.add( Integer.toString(resultSet.getInt(2)));
                chainStoresNamesIds.add(row);
            }

            //--------------------shops_addresses | prices | copies_num-------------------------------------------//
            resultSet=dbConfig.executeQuery(
             "select shop.chain_store_id, shop.address, concrete_book_shop.price, concrete_book_shop.copies_number\n" +
                     "from shop left outer join concrete_book_shop\n" +
                         "on ((shop.id=concrete_book_shop.shop_id) &\n" +
                             "(concrete_book_shop.book_id="+id+") & (concrete_book_shop.copies_number>0))\n" +
                                 "order by shop.chain_store_id");
            ArrayList<ArrayList<String>> shopsAddresses=new ArrayList<ArrayList<String>>();
            int i=0;
            ArrayList<String> row=new ArrayList<String>();
            //т к первый раз id будут совпадать, но имя сети магазинов добавить-то надо
            if (resultSet.next()){
                int price=resultSet.getInt("price");
                if (price!=0) { // т к испльзовался left join, слева price и copies м б null, если id не равны или копии=0
                    row.add(chainStoresNamesIds.get(i).get(0));//ch store name
                    addAddrPriceCopies(resultSet, row, price);
                }
            }
            while(resultSet.next()){
                //если id равны, то не нужно снова добавлять имя сети маагазинов, продолжаем заполнять строку
               if (resultSet.getInt(1)==Integer.parseInt(chainStoresNamesIds.get(i).get(1))) { //ch st id
                   int price=resultSet.getInt("price");
                   if (price!=0) {
                       addAddrPriceCopies(resultSet, row, price);
                   }
               }
               else {
                   shopsAddresses.add(row);
                   row=new ArrayList<String>();
                   i++;
                   int price=resultSet.getInt("price");
                   if (price!=0) {
                       row.add(chainStoresNamesIds.get(i).get(0));//ch store name
                       addAddrPriceCopies(resultSet, row, price);
                   }
               }
            }
            shopsAddresses.add(row);

            /*выходной лист вида
            ch_st_name | address | price | copies | address | price | copies...
            ch_st_name | address | price | copies...
            */
            modelMap.addAttribute("shopsAddresses", shopsAddresses);

        } catch (SQLException e) {
            return "error";
        }
        return "bookInfo";
    }

    @RequestMapping(value = "/admin")
    public String admin(ModelMap modelMap){
        try {
            ResultSet resultSet=dbConfig.executeQuery("select id, name from book");
            resultSet=dbConfig.executeQuery("select id, name from newspaper");
            resultSet=dbConfig.executeQuery("select id, name from magazine");
            resultSet=dbConfig.executeQuery("select id, name from shop");
            resultSet=dbConfig.executeQuery("select id, name from chain_store");
            resultSet=dbConfig.executeQuery("select * from concrete_book_shop");
            resultSet=dbConfig.executeQuery("select * from concrete_newspaper_shop");
            resultSet=dbConfig.executeQuery("select * from concrete_magazine_shop");

        } catch (SQLException e) {
            return "error";
        }
        return "admin";
    }

}

