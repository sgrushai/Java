package ua.lviv.lgs;

public class Main {
    public static void main(String[] args) {

//        Configuration configuration = new Configuration().configure();
//        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//        SessionFactory factory = configuration.buildSessionFactory(registry);
        GoodsDao gDao = new GoodsDao();



        Goods goods = new Goods("Подставка для телефона / смартфона / GPS навигация на руль", "Подставка для телефона / смартфона / GPS навигация на руль - BIC Original 5+ ",
                199,"gps", "BIC Original 5+", 10, 0.5,
                "http://velo-fox.com/image/cache/data/00499__Podstavka_dlja_telefona_smartfona_GPS_navigacija_na_rul_BIC_Original-500x500.jpg");

        Feedback feedbacks = new Feedback("user","gmail.com", "description", 5);

        feedbacks.setGoods(goods);
        goods.getFeedbacks().add(feedbacks);

        gDao.create(goods);

		System.out.println("Done");
        HibernateUtils.shutdown();
	}

}
