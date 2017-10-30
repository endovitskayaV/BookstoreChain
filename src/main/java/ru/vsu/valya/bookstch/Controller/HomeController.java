package ru.vsu.valya.bookstch.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.valya.bookstch.Model.*;
import ru.vsu.valya.bookstch.db.config.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/error")
    public String error() {
        return "error";
    }


    @RequestMapping(value = "/newspapers")
    public String newspapers(ModelMap modelMap) throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery("select id, name, issue from newspaper");
        ArrayList<Newspaper> outputList = new ArrayList<Newspaper>();
        while (resultSet.next()) {
            Newspaper newspaper = new Newspaper()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setIssue(resultSet.getInt("issue"));
            outputList.add(newspaper);
        }

        modelMap.addAttribute("newspapers", outputList);
        dbConnection.closeConnection();
        return "newspapers";
    }

    @RequestMapping(value = "/books")
    public String books(ModelMap modelMap) throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery("select id, name, author from book");
        ArrayList<Book> outputList = new ArrayList<Book>();

        while (resultSet.next()) {
            Book book = new Book()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setAuthor(resultSet.getString("author"));
            outputList.add(book);
        }

        modelMap.addAttribute("books", outputList);
        dbConnection.closeConnection();
        return "books";
    }

    @RequestMapping(value = "/magazines")
    public String magazines(ModelMap modelMap) throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery("select id, name, issue from magazine");
        ArrayList<Magazine> outputList = new ArrayList<Magazine>();

        while (resultSet.next()) {
            Magazine magazine = new Magazine()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setIssue(resultSet.getInt("issue"));
            outputList.add(magazine);
        }

        modelMap.addAttribute("magazines", outputList);
        dbConnection.closeConnection();
        return "magazines";
    }

    @RequestMapping(value = "/bookInfo")
    public String bookInfo(int id, ModelMap modelMap) throws SQLException, ClassNotFoundException {

        //------------------------------------abstract book parameters-------------------------------//
        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery("select * from book where id=" + id);
        while (resultSet.next()) {
            Book book = new Book()
                    .setName(resultSet.getString(2))
                    .setAuthor(resultSet.getString(3))
                    .setPublisher(resultSet.getString(4))
                    .setReleaseYear(resultSet.getDate(5))
                    .setPagesNumber(resultSet.getInt(6));
            modelMap.addAttribute("book", book);
        }

        dbConnection.closeConnection();

        //----------------------------------chainStores---------------------------------------------//
        dbConnection = DBConnection.newInstance();
        resultSet = dbConnection.executeQuery(
                "select distinct(name), id  from chain_store where id=any(" +
                        "select distinct(chain_store_id)  from shop        where id=any(" +
                        "select shop_id  from concrete_book_shop   where (copies_number>0) & (book_id=" + id + ")))" +
                        "order by id asc");
        ArrayList<ChainStore> chainStores = new ArrayList<ChainStore>();

        while (resultSet.next()) {
            ChainStore chainStore = new ChainStore()
                    .setName(resultSet.getString("name"))
                    .setId(resultSet.getInt("id"));
            chainStores.add(chainStore);
        }

        dbConnection.closeConnection();
        modelMap.addAttribute("chainStores", chainStores);

        //--------------------shops | prices | copies_num-------------------------------------------//
        dbConnection = DBConnection.newInstance();
        resultSet = dbConnection.executeQuery(
                "select shop.chain_store_id, shop.address, concrete_book_shop.price, concrete_book_shop.copies_number, shop.id\n" +
                        "from shop left outer join concrete_book_shop\n" +
                        "on ((shop.id=concrete_book_shop.shop_id) &\n" +
                        "(concrete_book_shop.book_id=" + id + ") & (concrete_book_shop.copies_number>0))\n" +
                        "order by shop.chain_store_id");
        ArrayList<Shop> shops = new ArrayList<Shop>();
        ArrayList<ConcreteBookShop> priceCopies = new ArrayList<ConcreteBookShop>();
        while (resultSet.next()) {
            if (chainStores.size() > 0) {
                int price = resultSet.getInt(3);
                if (price > 0) {
                    int shopId = resultSet.getInt(5);
                    Shop shop = new Shop()
                            .setId(shopId)
                            .setChainStoreId(resultSet.getInt(1))
                            .setAddress(resultSet.getString(2));
                    shops.add(shop);

                    ConcreteBookShop concrBookSh = new ConcreteBookShop()
                            .setShopId(shopId)
                            .setPrice(price)
                            .setCopiesNumber(resultSet.getInt(4));
                    priceCopies.add(concrBookSh);
                }
            }
        }

        modelMap.addAttribute("shops", shops);
        modelMap.addAttribute("priceCopiesList", priceCopies);
        dbConnection.closeConnection();
        return "bookInfo";
    }

    @RequestMapping(value = "/magazineInfo")
    public String magazineInfo(int id, ModelMap modelMap) throws SQLException, ClassNotFoundException {

        //------------------------------------abstract newspaper parameters-------------------------------//
        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery("select * from magazine where id=" + id);
        while (resultSet.next()) {
            Magazine magazine = new Magazine()
                    .setName(resultSet.getString(2))
                    .setReleaseDate(resultSet.getDate(3))
                    .setIssue(resultSet.getInt(4))
                    .setPagesNumber(resultSet.getInt(5));
            modelMap.addAttribute("magazine", magazine);
        }
        dbConnection.closeConnection();

        //--------------------------chainStores---------------------------------------------------//
        dbConnection = DBConnection.newInstance();
        resultSet = dbConnection.executeQuery(
                "select distinct(name), id  from chain_store where id=any(" +
                        "select distinct(chain_store_id)  from shop        where id=any(" +
                        "select shop_id  from concrete_magazine_shop   where (copies_number>0) & (magazine_id=" + id + ")))" +
                        "order by id asc");
        ArrayList<ChainStore> chainStores = new ArrayList<ChainStore>();

        while (resultSet.next()) {
            ChainStore chainStore = new ChainStore()
                    .setName(resultSet.getString("name"))
                    .setId(resultSet.getInt("id"));
            chainStores.add(chainStore);
        }

        dbConnection.closeConnection();
        modelMap.addAttribute("chainStores", chainStores);

        //--------------------shops_addresses | prices | copies_num-------------------------------------------//
        dbConnection = DBConnection.newInstance();
        resultSet = dbConnection.executeQuery(
                "select shop.chain_store_id, shop.address, concrete_magazine_shop.price, concrete_magazine_shop.copies_number, shop.id\n" +
                        "from shop left outer join concrete_magazine_shop\n" +
                        "on ((shop.id=concrete_magazine_shop.shop_id) &\n" +
                        "(concrete_magazine_shop.magazine_id=" + id + ") & (concrete_magazine_shop.copies_number>0))\n" +
                        "order by shop.chain_store_id");
        ArrayList<Shop> shops = new ArrayList<Shop>();
        ArrayList<ConcreteMagazineShop> priceCopies = new ArrayList<ConcreteMagazineShop>();
        while (resultSet.next()) {
            if (chainStores.size() > 0) {
                int price = resultSet.getInt(3);
                if (price > 0) {
                    int shopId = resultSet.getInt(5);
                    Shop shop = new Shop()
                            .setId(shopId)
                            .setChainStoreId(resultSet.getInt(1))
                            .setAddress(resultSet.getString(2));
                    shops.add(shop);

                    ConcreteMagazineShop concrMagazSh = new ConcreteMagazineShop()
                            .setShopId(shopId)
                            .setPrice(price)
                            .setCopiesNumber(resultSet.getInt(4));
                    priceCopies.add(concrMagazSh);
                }
            }
        }

        modelMap.addAttribute("shops", shops);
        modelMap.addAttribute("priceCopiesList", priceCopies);
        dbConnection.closeConnection();
        return "magazineInfo";
    }

    @RequestMapping(value = "/newspaperInfo")
    public String newspaperInfo(int id, ModelMap modelMap) throws SQLException, ClassNotFoundException {

        //-----------------------------abstract newspaper parameters--------------------------------//
        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery("select * from newspaper where id=" + id);
        while (resultSet.next()) {
            Newspaper newspaper = new Newspaper()
                    .setName(resultSet.getString(2))
                    .setReleaseDate(resultSet.getDate(3))
                    .setIssue(resultSet.getInt(4));
            modelMap.addAttribute("newspaper", newspaper);
        }
        dbConnection.closeConnection();

        //----------------------------------chainStores---------------------------------------------//
        dbConnection = DBConnection.newInstance();
        resultSet = dbConnection.executeQuery(
                "select distinct(name), id  from chain_store where id=any(" +
                        "select distinct(chain_store_id)  from shop        where id=any(" +
                        "select shop_id  from concrete_newspaper_shop   " +
                        "where (copies_number>0) & (newspaper_id=" + id + ")))" +
                        "order by id asc");
        ArrayList<ChainStore> chainStores = new ArrayList<ChainStore>();

        while (resultSet.next()) {
            ChainStore chainStore = new ChainStore()
                    .setName(resultSet.getString("name"))
                    .setId(resultSet.getInt("id"));
            chainStores.add(chainStore);
        }

        dbConnection.closeConnection();
        modelMap.addAttribute("chainStores", chainStores);

        dbConnection = DBConnection.newInstance();
        //--------------------------------------shops | prices | copies_num-----------------------------------------------//
        resultSet = dbConnection.executeQuery(
                "select shop.chain_store_id, shop.address, concrete_newspaper_shop.price, concrete_newspaper_shop.copies_number, shop.id\n" +
                        "from shop left outer join concrete_newspaper_shop\n" +
                        "on ((shop.id=concrete_newspaper_shop.shop_id) &\n" +
                        "(concrete_newspaper_shop.newspaper_id=" + id + ") & (concrete_newspaper_shop.copies_number>0))\n" +
                        "order by shop.chain_store_id");
        ArrayList<Shop> shops = new ArrayList<Shop>();
        ArrayList<ConcreteNewspaperShop> priceCopies = new ArrayList<ConcreteNewspaperShop>();
        while (resultSet.next()) {
            if (chainStores.size() > 0) {
                int price = resultSet.getInt(3);
                if (price > 0) {
                    int shopId = resultSet.getInt(5);
                    Shop shop = new Shop()
                            .setId(shopId)
                            .setChainStoreId(resultSet.getInt(1))
                            .setAddress(resultSet.getString(2));
                    shops.add(shop);

                    ConcreteNewspaperShop concrNewspSh = new ConcreteNewspaperShop()
                            .setShopId(shopId)
                            .setPrice(price)
                            .setCopiesNumber(resultSet.getInt(4));
                    priceCopies.add(concrNewspSh);
                }
            }
        }

        modelMap.addAttribute("shops", shops);
        modelMap.addAttribute("priceCopiesList", priceCopies);
        dbConnection.closeConnection();
        return "newspaperInfo";
    }

    @RequestMapping(value = "/admin")
    public String admin(ModelMap modelMap) throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery("select id, name from book");
        resultSet = dbConnection.executeQuery("select id, name from newspaper");
        resultSet = dbConnection.executeQuery("select id, name from magazine");
        resultSet = dbConnection.executeQuery("select id, name from shop");
        resultSet = dbConnection.executeQuery("select id, name from chain_store");
        resultSet = dbConnection.executeQuery("select * from concrete_book_shop");
        resultSet = dbConnection.executeQuery("select * from concrete_newspaper_shop");
        resultSet = dbConnection.executeQuery("select * from concrete_magazine_shop");
        return "admin";
    }

}
