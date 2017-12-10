package ru.vsu.valya.bookstch.Controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vsu.valya.bookstch.Model.*;
import ru.vsu.valya.bookstch.db.config.DBConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

@Controller
public class HomeController {

    @RequestMapping(value = "/error")
    public String error(ModelMap modelMap, String msg) {
        modelMap.addAttribute("error", msg);
        return "error";
    }

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
                    .setReleaseYear(resultSet.getInt(5))
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
                    .setReleaseYear(resultSet.getInt(3))
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
                    .setReleaseYear(resultSet.getInt(3))
                    .setIssue(resultSet.getInt(4));
            modelMap.addAttribute("newspaper", newspaper);
        }
        dbConnection.closeConnection();

        setAvailabilityModel("newspaper", id, modelMap);

        return "newspaperInfo";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
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
        resultSet = dbConnection.executeQuery("select id, address, chain_store_id from shop");
        ArrayList<Shop> shops = new ArrayList<Shop>();
        while (resultSet.next()) {
            Shop shop = new Shop()
                    .setId(resultSet.getInt(1))
                    .setAddress(resultSet.getString(2))
                    .setChainStoreId(resultSet.getInt(3));
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

        return "admin";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public String addBook(ModelMap modelMap) {
        Book book = new Book();
        modelMap.addAttribute("book", book);
        modelMap.addAttribute("maxYear", Calendar.getInstance().get(Calendar.YEAR));
        return "addBook";
    }

    @RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public String addBook(ModelMap modelMap, Book book) throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        dbConnection.executeUpdate(
                "insert into `Book` " +
                        "(`id`, `name`, `author`, `publisher`, `release_year`, `page_numbers`) \n" +
                        "value (" +
                        "NULL, " +
                        "'" + book.getName() + "', " +
                        "'" + book.getAuthor() + "', " +
                        "'" + book.getPublisher() + "', " +
                        "'" + book.getReleaseYear() + "', " +
                        "'" + book.getPagesNumber() + "');");
        dbConnection.closeConnection();
        return admin(modelMap);
    }

    @RequestMapping(value = "/addNewspaper", method = RequestMethod.GET)
    public String addNewspaper(ModelMap modelMap) {
       Newspaper newspaper= new Newspaper();
        modelMap.addAttribute("newspaper", newspaper);
        modelMap.addAttribute("maxYear", Calendar.getInstance().get(Calendar.YEAR));
        return "addNewspaper";
    }

    @RequestMapping(value = "/addNewspaper", method = RequestMethod.POST)
    public String addNewspaper(ModelMap modelMap, Newspaper newspaper) throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        dbConnection.executeUpdate(
                "insert into `Newspaper` " +
                        "(`id`, `name`, `release_year`, `issue`) \n" +
                        "value (" +
                        "NULL, " +
                        "'" + newspaper.getName() + "', " +
                        "'" + newspaper.getReleaseYear() + "', " +
                        "'" + newspaper.getIssue() + "');");
        dbConnection.closeConnection();
        return admin(modelMap);
    }

    @RequestMapping(value = "/addMagazine", method = RequestMethod.GET)
    public String addMagazine(ModelMap modelMap) {
        Magazine magazine=new Magazine();
        modelMap.addAttribute("magazine", magazine);
        modelMap.addAttribute("maxYear", Calendar.getInstance().get(Calendar.YEAR));
        return "addMagazine";
    }

    @RequestMapping(value = "/addMagazine", method = RequestMethod.POST)
    public String addMagazine(ModelMap modelMap, Magazine magazine) throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        dbConnection.executeUpdate(
                "insert into `Magazine` " +
                        "(`id`, `name`, `release_year`, `issue`, `page_numbers`) \n" +
                        "value (" +
                        "NULL, " +
                        "'" + magazine.getName() + "', " +
                        "'" + magazine.getReleaseYear() + "', " +
                        "'" + magazine.getIssue() + "', " +
                        "'" + magazine.getPagesNumber() +"');");
        dbConnection.closeConnection();
        return admin(modelMap);
    }


    @RequestMapping(value = "/addAvailabilityInfo", method = RequestMethod.GET)
    public String addAvailabilityInfo(ModelMap modelMap, int itemId, String itemName, String backAddr)
            throws SQLException, ClassNotFoundException {
        ConcreteProductInShop concreteProductInShop = new ConcreteProductInShop()
                .setItemId(itemId)
                .setItemName(itemName);
        modelMap.addAttribute("concreteProductInShop", concreteProductInShop);

        //-------------------shops that can be added----------------------------------//
        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery("select id, address, chain_store_id from shop\n" +
                "where id not in (select shop_id from concrete_" + itemName + "_shop\n" +
                "where "+itemName+"_id=" + itemId + " and copies_number>0);");


        ArrayList<Shop> shops = new ArrayList<Shop>();
        while (resultSet.next()) {
            Shop shop = new Shop()
                    .setId(resultSet.getInt(1))
                    .setAddress(resultSet.getString(2))
                    .setChainStoreId(resultSet.getInt(3));
            shops.add(shop);
        }
        modelMap.addAttribute("shops", shops);
        modelMap.addAttribute("backAddr", backAddr);
        dbConnection.closeConnection();


        //-------------------chain_stores  that can be added----------------------------------//
        dbConnection = DBConnection.newInstance();
        resultSet = dbConnection.executeQuery("select id, name from chain_store\n" +
                "where id in (select chain_store_id from shop\n" +
                "where id not in (select shop_id from concrete_" + itemName + "_shop\n" +
                "where "+itemName+"_id=" + itemId + " and copies_number>0));");


        ArrayList<ChainStore> chainStores = new ArrayList<ChainStore>();
        while (resultSet.next()) {
            ChainStore chainStore = new ChainStore()
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2));
            chainStores.add(chainStore);
        }
        modelMap.addAttribute("chainStores", chainStores);
        dbConnection.closeConnection();


        return "addAvailabilityInfo";
    }

    @RequestMapping(value = "/addAvailabilityInfo", method = RequestMethod.POST)
    public String addAvailabilityInfo(ModelMap modelMap, String backAddr,
                                      ConcreteProductInShop concreteProductInShop)
            throws SQLException, ClassNotFoundException {
        if(concreteProductInShop.getCopiesNumber()>0) {
            DBConnection dbConnection = DBConnection.newInstance();
            dbConnection.executeUpdate(
                    "insert into " +
                            "`concrete_" + concreteProductInShop.getItemName().toLowerCase() + "_shop` " +
                            "(`"+concreteProductInShop.getItemName()+"_id`," +
                            " `shop_id`, `price`, `copies_number`) \n" +
                            "value (" +
                            "'" + concreteProductInShop.getItemId() + "', " +
                            "'" + concreteProductInShop.getShopId() + "', " +
                            "'" + concreteProductInShop.getPrice() + "', " +
                            "'" + concreteProductInShop.getCopiesNumber() + "');");

            dbConnection.closeConnection();
        }
        switch (backAddr){
            case "editBook": return editBook(modelMap, concreteProductInShop.getItemId());
            case "editNewspaper": return editNewspaper(modelMap, concreteProductInShop.getItemId());
            case "editMagazine": return editMagazine(modelMap, concreteProductInShop.getItemId());
            default:       return admin(modelMap);
        }
    }

    @RequestMapping(value = "/editBook", method = RequestMethod.GET)
    public String editBook(ModelMap modelMap, int id) throws SQLException, ClassNotFoundException {

        //-----------------book---------------------------------------------------//
        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery(
                "select id, name, author, publisher, release_year, page_numbers" +
                        " from book " +
                        "where id=" + id);
        Book book = new Book();
        while (resultSet.next()) {
            book
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2))
                    .setAuthor(resultSet.getString(3))
                    .setPublisher(resultSet.getString(4))
                    .setReleaseYear(resultSet.getInt(5))
                    .setPagesNumber(resultSet.getInt(6));
        }

        modelMap.addAttribute("book", book);
        dbConnection.closeConnection();

        setAvailabilityModel("book", book.getId(), modelMap);
        modelMap.addAttribute("backAddr", "editBook");
        return "editBook";
    }

    @RequestMapping(value = "/editBook",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editBook(ModelMap modelMap, Book book)
            throws SQLException, ClassNotFoundException {
        //----------------------update book---------------------//
        DBConnection dbConnection = DBConnection.newInstance();
        dbConnection.executeUpdate(
                "update book set " +
                        "name='" + book.getName() + "'," +
                        "author='" + book.getAuthor() + "'," +
                        "publisher='" + book.getPublisher() + "'," +
                        "release_year='" + book.getReleaseYear() + "'," +
                        "page_numbers='" + book.getPagesNumber() + "'" +
                        "where id=" + book.getId());

        dbConnection.closeConnection();

        Arrays.asList(book.getConcreteProductInShopArr()).forEach(x -> {
            try {
                if (x != null) editConcrete(x);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return admin(modelMap);
    }

    @RequestMapping(value = "/editNewspaper", method = RequestMethod.GET)
    public String editNewspaper(ModelMap modelMap, int id) throws SQLException, ClassNotFoundException {

        //-----------------newspaper---------------------------------------------------//
        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery(
                "select id, name,  release_year, issue" +
                        " from newspaper " +
                        "where id=" + id);
      Newspaper newspaper=new Newspaper();
        while (resultSet.next()) {
           newspaper
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2))
                    .setReleaseYear(resultSet.getInt(3))
                    .setIssue(resultSet.getInt(4));
        }

        modelMap.addAttribute("newspaper", newspaper);
        dbConnection.closeConnection();

        setAvailabilityModel("newspaper", newspaper.getId(), modelMap);
        modelMap.addAttribute("backAddr", "editNewspaper");
        return "editNewspaper";
    }


    @RequestMapping(value = "/editNewspaper",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editNewspaper(ModelMap modelMap, Newspaper newspaper)
            throws SQLException, ClassNotFoundException {
        //----------------------update book---------------------//
        DBConnection dbConnection = DBConnection.newInstance();
        dbConnection.executeUpdate(
                "update newspaper set " +
                        "name='" + newspaper.getName() + "'," +
                        "release_year='" + newspaper.getReleaseYear() + "'," +
                        "issue='" + newspaper.getIssue() + "'" +
                        "where id=" + newspaper.getId());

        dbConnection.closeConnection();

        Arrays.asList(newspaper.getConcreteProductInShopArr()).forEach(x -> {
            try {
                if (x != null) editConcrete(x);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return admin(modelMap);
    }

    @RequestMapping(value = "/editMagazine", method = RequestMethod.GET)
    public String editMagazine(ModelMap modelMap, int id) throws SQLException, ClassNotFoundException {

        //-----------------magazine---------------------------------------------------//
        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery(
                "select id, name,  release_year, issue,  page_numbers" +
                        " from magazine " +
                        "where id=" + id);
      Magazine magazine=new Magazine();
        while (resultSet.next()) {
            magazine
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2))
                    .setReleaseYear(resultSet.getInt(3))
                    .setIssue(resultSet.getInt(4))
                    .setPagesNumber(resultSet.getInt(5));
        }

        modelMap.addAttribute("magazine", magazine);
        dbConnection.closeConnection();

        setAvailabilityModel("magazine", magazine.getId(), modelMap);
        modelMap.addAttribute("backAddr", "editMagazine");
        return "editMagazine";
    }

    @RequestMapping(value = "/editMagazine",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editMagazine(ModelMap modelMap, Magazine magazine)
            throws SQLException, ClassNotFoundException {
        //----------------------update book---------------------//
        DBConnection dbConnection = DBConnection.newInstance();
        dbConnection.executeUpdate(
                "update magazine set " +
                        "name='" + magazine.getName() + "', " +
                        "release_year='" + magazine.getReleaseYear() + "', " +
                        "issue='" + magazine.getIssue() + "', " +
                        "page_numbers='" + magazine.getPagesNumber() + "' " +
                        "where id=" + magazine.getId());

        dbConnection.closeConnection();

        Arrays.asList(magazine.getConcreteProductInShopArr()).forEach(x -> {
            try {
                if (x != null) editConcrete(x);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        return admin(modelMap);
    }

    private void editConcrete(ConcreteProductInShop concreteProductInShop) throws SQLException, ClassNotFoundException {
        DBConnection dbConnection = DBConnection.newInstance();
        dbConnection.executeUpdate(
                "update concrete_" + concreteProductInShop.getItemName().toLowerCase() + "_shop set " +
                        "price='" + concreteProductInShop.getPrice() + "', " +
                        "copies_number='" + concreteProductInShop.getCopiesNumber() + "' " +
                        "where (" + concreteProductInShop.getItemName().toLowerCase() +
                        "_id=" + concreteProductInShop.getItemId() +
                        " and shop_id=" + concreteProductInShop.getShopId() + ")");

        dbConnection.closeConnection();
    }

    @RequestMapping(value = "/deleteBook")
    public String deleteBook(ModelMap modelMap, int id) throws SQLException, ClassNotFoundException {

        try {
            DBConnection dbConnection = DBConnection.newInstance();
            dbConnection.executeQuery(
                    "delete from book where id=" + id);
            dbConnection.closeConnection();
        } catch (SQLException e) {
            return error(modelMap, "cannot delete");
        } catch (ClassNotFoundException e) {
            return error(modelMap, "cannot delete");
        }

        return admin(modelMap);
    }

    @RequestMapping(value = "/deleteNewspaper")
    public String deleteNewspaper(ModelMap modelMap, int id) throws SQLException, ClassNotFoundException {

        try {
            DBConnection dbConnection = DBConnection.newInstance();
            dbConnection.executeQuery(
                    "delete from newspaper where id=" + id);
            dbConnection.closeConnection();
        } catch (SQLException e) {
            return error(modelMap, "cannot delete");
        } catch (ClassNotFoundException e) {
            return error(modelMap, "cannot delete");
        }

        return admin(modelMap);
    }

    @RequestMapping(value = "/deleteMagazine")
    public String deleteMagazine(ModelMap modelMap, int id) throws SQLException, ClassNotFoundException {

        try {
            DBConnection dbConnection = DBConnection.newInstance();
            dbConnection.executeQuery(
                    "delete from magazine where id=" + id);
            dbConnection.closeConnection();
        } catch (SQLException e) {
            return error(modelMap, "cannot delete");
        } catch (ClassNotFoundException e) {
            return error(modelMap, "cannot delete");
        }

        return admin(modelMap);
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
        ArrayList<ConcreteProductInShop> priceCopies = new ArrayList<ConcreteProductInShop>();
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

                    ConcreteProductInShop concreteProductInShop = new ConcreteProductInShop()
                            .setShopId(shopId)
                            .setPrice(price)
                            .setCopiesNumber(resultSet.getInt(4));
                    priceCopies.add(concreteProductInShop);
                }
            }
        }

        modelMap.addAttribute("i", 0);
        modelMap.addAttribute("itemName", itemType.toLowerCase());
        modelMap.addAttribute("itemId", id);
        modelMap.addAttribute("shops", shops);
        modelMap.addAttribute("priceCopiesList", priceCopies);
        dbConnection.closeConnection();
    }

    @RequestMapping(value = "/addChainStore", method = RequestMethod.GET)
    public String addChainStore(ModelMap modelMap){
       ChainStore chainStore=new ChainStore();
        modelMap.addAttribute("chainStore", chainStore);
        return "addChainStore";
    }

    @RequestMapping(value = "/addChainStore", method = RequestMethod.POST)
    public String addChainStore(ModelMap modelMap, ChainStore chainStore) throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        dbConnection.executeUpdate(
                "insert into `chain_store` " +
                        "(`id`, `name`) \n" +
                        "value (" +
                        "NULL, " +
                        "'" + chainStore.getName() + "');");
        dbConnection.closeConnection();
        return admin(modelMap);
    }

    @RequestMapping(value = "/editChainStore", method = RequestMethod.GET)
    public String editChainStore(ModelMap modelMap, int id) throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery(
                "select id, name" +
                        " from chain_store " +
                        "where id=" + id);
        ChainStore chainStore=new ChainStore();
        while (resultSet.next()) {
           chainStore
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2));
        }

        modelMap.addAttribute("chainStore", chainStore);
        dbConnection.closeConnection();
        return "editChainStore";
    }

    @RequestMapping(value = "/editChainStore", method = RequestMethod.POST)
    public String editChainStore(ModelMap modelMap, ChainStore chainStore)
            throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        dbConnection.executeUpdate(
                "update chain_store set " +
                        "name='" + chainStore.getName() + "'" +
                        "where id=" + chainStore.getId());

        dbConnection.closeConnection();

        return admin(modelMap);
    }

    @RequestMapping(value = "/deleteChainStore")
    public String deleteChainStore(ModelMap modelMap, int id) throws SQLException, ClassNotFoundException {

        try {
            DBConnection dbConnection = DBConnection.newInstance();
            dbConnection.executeQuery(
                    "delete from chain_store where id=" + id);
            dbConnection.closeConnection();
        } catch (SQLException e) {
            return error(modelMap, "cannot delete");
        } catch (ClassNotFoundException e) {
            return error(modelMap, "cannot delete");
        }

        return admin(modelMap);
    }

    @RequestMapping(value = "/addShop", method = RequestMethod.GET)
    public String addShop(ModelMap modelMap)throws SQLException, ClassNotFoundException{
        Shop shop=new Shop();
        modelMap.addAttribute("shop", shop);

        //-------------------chain_stores----------------------------------//
       DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery("select id, name from chain_store");

        ArrayList<ChainStore> chainStores = new ArrayList<>();
        while (resultSet.next()) {
            ChainStore chainStore = new ChainStore()
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2));
            chainStores.add(chainStore);
        }
        modelMap.addAttribute("chainStores", chainStores);
        dbConnection.closeConnection();
        return "addShop";
    }

    @RequestMapping(value = "/addShop", method = RequestMethod.POST)
    public String addShop(ModelMap modelMap,Shop shop)
            throws SQLException, ClassNotFoundException {

            DBConnection dbConnection = DBConnection.newInstance();
            dbConnection.executeUpdate(
                    "insert into shop" +
                            "(`id`, `address`, `chain_store_id`) \n" +
                            "value (" +
                            "'" + shop.getId() + "', " +
                            "'" + shop.getAddress() + "', " +
                            "'" + shop.getChainStoreId() + "');");

            dbConnection.closeConnection();
         return admin(modelMap);
        }

    @RequestMapping(value = "/editShop", method = RequestMethod.GET)
    public String editShop(ModelMap modelMap, int id) throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        ResultSet resultSet = dbConnection.executeQuery(
                "select id, address, chain_store_id" +
                        " from shop " +
                        "where id=" + id);
        Shop shop=new Shop();
        while (resultSet.next()) {
            shop
                    .setId(resultSet.getInt(1))
                    .setAddress(resultSet.getString(2))
                    .setChainStoreId(resultSet.getInt(3));
        }

        modelMap.addAttribute("shop", shop);
        dbConnection.closeConnection();

        //--------------------chain_store------------------//
        dbConnection = DBConnection.newInstance();
        resultSet = dbConnection.executeQuery("select id, name from chain_store\n" +
                "where id="+shop.getChainStoreId());


        ArrayList<ChainStore> chainStores = new ArrayList<ChainStore>();
        while (resultSet.next()) {
            ChainStore chainStore = new ChainStore()
                    .setId(resultSet.getInt(1))
                    .setName(resultSet.getString(2));
            chainStores.add(chainStore);
        }
        modelMap.addAttribute("chainStores", chainStores);
        dbConnection.closeConnection();

        return "editShop";
    }

    @RequestMapping(value = "/editShop", method = RequestMethod.POST)
    public String editShop(ModelMap modelMap, Shop shop)
            throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = DBConnection.newInstance();
        dbConnection.executeUpdate(
                "update shop set " +
                        "address='" + shop.getAddress() + "', " +
                        "chain_store_id='" + shop.getChainStoreId() + "' " +
                        "where id=" + shop.getId());

        dbConnection.closeConnection();

        return admin(modelMap);
    }

    @RequestMapping(value = "/deleteShop")
    public String deleteShop(ModelMap modelMap, int id) throws SQLException, ClassNotFoundException {

        try {
            DBConnection dbConnection = DBConnection.newInstance();
            dbConnection.executeQuery(
                    "delete from shop where id=" + id);
            dbConnection.closeConnection();
        } catch (SQLException e) {
            return error(modelMap, "cannot delete");
        } catch (ClassNotFoundException e) {
            return error(modelMap, "cannot delete");
        }

        return admin(modelMap);
    }


}

