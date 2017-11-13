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

    //TODO: try-catch-finally to close session
    @RequestMapping(value = "/newspapers")
    public String newspapers(ModelMap modelMap) throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery("select id, name, issue from newspaper");

        ArrayList<Newspaper> newspapers = new ArrayList<Newspaper>();
        while (resultSet.next()) {
            Newspaper newspaper = new Newspaper()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setIssue(resultSet.getInt("issue"));
            newspapers.add(newspaper);
        }

        modelMap.addAttribute("newspapers", newspapers);
        dbConnection.closeConnection();
        return "newspapers";
    }

    @RequestMapping(value = "/books")
    public String books(ModelMap modelMap) throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery("select id, name, author from book");
        ArrayList<Book> books = new ArrayList<Book>();

        while (resultSet.next()) {
            Book book = new Book()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setAuthor(resultSet.getString("author"));
            books.add(book);
        }

        modelMap.addAttribute("books", books);
        dbConnection.closeConnection();
        return "books";
    }

    @RequestMapping(value = "/magazines")
    public String magazines(ModelMap modelMap) throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery("select id, name, issue from magazine");
        ArrayList<Magazine> magazines = new ArrayList<Magazine>();

        while (resultSet.next()) {
            Magazine magazine = new Magazine()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .setIssue(resultSet.getInt("issue"));
            magazines.add(magazine);
        }

        modelMap.addAttribute("magazines", magazines);
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

        setAvailabilityModel("book", id, modelMap);
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

        setAvailabilityModel("magazine", id, modelMap);
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

        setAvailabilityModel("newspaper", id, modelMap);

        return "newspaperInfo";
    }

    @RequestMapping(value = "/admin")
    public String admin(ModelMap modelMap) throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery("select id, name, author from book");
        ArrayList<Book> books = new ArrayList<Book>();
        while (resultSet.next()) {
            Book book = new Book()
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2))
                    .setAuthor(resultSet.getString(3));
            books.add(book);
        }
        modelMap.addAttribute("books", books);
        dbConnection.closeConnection();

        dbConnection = DBConnection.newInstance();
        resultSet = dbConnection.executeQuery("select id, name,issue from newspaper");
        ArrayList<Newspaper> newspapers = new ArrayList<Newspaper>();
        while (resultSet.next()) {
            Newspaper newspaper = new Newspaper()
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2))
                    .setIssue(resultSet.getInt(3));
            newspapers.add(newspaper);
        }
        modelMap.addAttribute("newspapers", newspapers);
        dbConnection.closeConnection();

        dbConnection = DBConnection.newInstance();
        resultSet = dbConnection.executeQuery("select id, name,issue from magazine");
        ArrayList<Magazine> magazines = new ArrayList<Magazine>();
        while (resultSet.next()) {
            Magazine magazine = new Magazine()
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2))
                    .setIssue(resultSet.getInt(3));
            magazines.add(magazine);
        }
        modelMap.addAttribute("magazines", magazines);
        dbConnection.closeConnection();

        dbConnection = DBConnection.newInstance();
        resultSet = dbConnection.executeQuery("select id, name, address from shop");
        ArrayList<Shop> shops = new ArrayList<Shop>();
        while (resultSet.next()) {
            Shop shop = new Shop()
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2))
                    .setAddress(resultSet.getString(3));
            shops.add(shop);
        }
        modelMap.addAttribute("shops", shops);
        dbConnection.closeConnection();

        dbConnection = DBConnection.newInstance();
        resultSet = dbConnection.executeQuery("select id, name from chain_store");
        ArrayList<ChainStore> chainStores = new ArrayList<ChainStore>();
        while (resultSet.next()) {
            ChainStore chainStore = new ChainStore()
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2));
            chainStores.add(chainStore);
        }
        modelMap.addAttribute("chainStores", chainStores);
        dbConnection.closeConnection();

        dbConnection = DBConnection.newInstance();
        resultSet = dbConnection.executeQuery("select * from concrete_newspaper_shop");
        ArrayList<ConcreteBookShop> concreteBooksShops=new ArrayList<ConcreteBookShop>();
        while (resultSet.next()){
            ConcreteBookShop concreteBookShop=new ConcreteBookShop()
                    .setBookId(resultSet.getInt(1))
                    .setShopId(resultSet.getInt(2))
                    .setPrice(resultSet.getInt(3))
                    .setCopiesNumber(resultSet.getInt(4));
            concreteBooksShops.add(concreteBookShop);
        }
        modelMap.addAttribute("concreteBooksShops", concreteBooksShops);
        dbConnection.closeConnection();

        dbConnection = DBConnection.newInstance();
        resultSet = dbConnection.executeQuery("select * from concrete_newspaper_shop");
        ArrayList<ConcreteNewspaperShop> concreteNewspapersShops=new ArrayList<ConcreteNewspaperShop>();
        while (resultSet.next()){
            ConcreteNewspaperShop concreteNewspaperShop=new ConcreteNewspaperShop()
                    .setNewspaperId(resultSet.getInt(1))
                    .setShopId(resultSet.getInt(2))
                    .setPrice(resultSet.getInt(3))
                    .setCopiesNumber(resultSet.getInt(4));
            concreteNewspapersShops.add(concreteNewspaperShop);
        }
        modelMap.addAttribute("concreteNewspapersShops", concreteNewspapersShops);
        dbConnection.closeConnection();

        dbConnection = DBConnection.newInstance();
        resultSet = dbConnection.executeQuery("select * from concrete_magazine_shop");
        ArrayList<ConcreteMagazineShop> concreteMagazinesShops=new ArrayList<ConcreteMagazineShop>();
        while (resultSet.next()){
            ConcreteMagazineShop concreteMagazineShop=new ConcreteMagazineShop()
                    .setMagazineId(resultSet.getInt(1))
                    .setShopId(resultSet.getInt(2))
                    .setPrice(resultSet.getInt(3))
                    .setCopiesNumber(resultSet.getInt(4));
            concreteMagazinesShops.add(concreteMagazineShop);
        }
        modelMap.addAttribute("concreteMagazinesShops", concreteMagazinesShops);
        dbConnection.closeConnection();

        return "admin";
    }

    private void setAvailabilityModel(String itemType, int id, ModelMap modelMap) throws SQLException, ClassNotFoundException {
        //----------------------------------chainStores---------------------------------------------//
        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery(
                "select distinct(name), id  from chain_store where id=any(" +
                        "select distinct(chain_store_id)  from shop        where id=any(" +
                        "select shop_id  from concrete_" + itemType + "_shop   " +
                        "where (copies_number>0) & (" + itemType + "_id=" + id + ")))" +
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
                "select shop.chain_store_id, shop.address, concrete_" + itemType + "_shop.price, concrete_" + itemType + "_shop.copies_number, shop.id\n" +
                        "from shop left outer join concrete_" + itemType + "_shop\n" +
                        "on ((shop.id=concrete_" + itemType + "_shop.shop_id) &\n" +
                        "(concrete_" + itemType + "_shop." + itemType + "_id=" + id + ") & (concrete_" + itemType + "_shop.copies_number>0))\n" +
                        "order by shop.chain_store_id");
        ArrayList<Shop> shops = new ArrayList<Shop>();
        ArrayList<ConcreteNewspaperShop> priceCopies = new ArrayList<ConcreteNewspaperShop>();
        while (resultSet.next()) {
            if (chainStores.size() > 0) {
                int price = resultSet.getInt(3);
                if (price > 0) { // тк left join правая часть (price и колво копий  мб =0)
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
    }
}

