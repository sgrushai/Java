package ua.lviv.lgs;

public class Main {
    public static void main(String[] args) {

        GoodsDao gDao = new GoodsDao();



        Goods goods1 = new Goods("Подставка для телефона / смартфона / GPS навигация на руль", "Подставка для телефона / смартфона / GPS навигация на руль - BIC Original 5+ ",
                199,"gps", "BIC Original 5+", 10, 0.5,
                "http://velo-fox.com/image/cache/data/00499__Podstavka_dlja_telefona_smartfona_GPS_navigacija_na_rul_BIC_Original-500x500.jpg");
//        Goods goods2 = new Goods("2Подставка для телефона / смартфона / GPS навигация на руль", "2Подставка для телефона / смартфона / GPS навигация на руль - BIC Original 5+ ",
//                199,"2gps", "2BIC Original 5+", 10, 0.5,
//                "2http://velo-fox.com/image/cache/data/00499__Podstavka_dlja_telefona_smartfona_GPS_navigacija_na_rul_BIC_Original-500x500.jpg");

        Order order1 = new Order("user","gmail.com", "description", 5);
        Order order2 = new Order("user","gmail.com", "description", 4);

        order1.getGoods().add(goods1);
        order2.getGoods().add(goods1);
        goods1.getOrders().add(order1);
        goods1.getOrders().add(order2);



        gDao.createF(order1);
        gDao.createF(order2);

		System.out.println("Done");
        HibernateUtils.shutdown();
	}

}
