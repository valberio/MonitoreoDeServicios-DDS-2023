package server;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static EntityManagerFactory entityManagerFactory;


    public static void main(String[] args) throws Exception {

        Map<String, String> env = System.getenv();

        entityManagerFactory =  createEntityManagerFactory();

        Server.init();
    }

    public static EntityManagerFactory createEntityManagerFactory() throws Exception {
        // https://stackoverflow.com/questions/8836834/read-environment-variables-in-persistence-xml-file
        Map<String, String> env = System.getenv();
        Map<String, Object> configOverrides = new HashMap<>();

        String[] keys = new String[] {
                "DATABASE_URL",
                "javax__persistence__jdbc__driver",
                "javax__persistence__jdbc__password",
                "javax__persistence__jdbc__url",
                "javax__persistence__jdbc__user",
                "hibernate__hbm2ddl__auto",
                "hibernate__connection__pool_size",
                "hibernate__show_sql" };

        for (String key : keys) {

            try{
                if (key.equals("DATABASE_URL")) {

                    // https://devcenter.heroku.com/articles/connecting-heroku-postgres#connecting-in-java
                    String value = env.get(key);
                    URI dbUri = new URI(value);
                    String username = dbUri.getUserInfo().split(":")[0];
                    String password = dbUri.getUserInfo().split(":")[1];
                    //javax.persistence.jdbc.url=jdbc:postgresql://localhost/dblibros
                    //value = "jdbc:mysql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();// + "?sslmode=require";
                    value = "jdbc:postgres://dpg-cll2k16aov6s73f1ne0g-a.oregon-postgres.render.com/dbtpama";
                    configOverrides.put("javax.persistence.jdbc.url", value);
                    configOverrides.put("javax.persistence.jdbc.user", username);
                    configOverrides.put("javax.persistence.jdbc.password", password);
                    configOverrides.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
                    //  configOverrides.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
                }
                // no se pueden poner variables de entorno con "." en la key
                String key2 = key.replace("__",".");
                if (env.containsKey(key)) {
                    String value = env.get(key);
                    configOverrides.put(key2, value);
                }
            } catch(Exception ex){
                System.out.println("Error configurando " + key);
            }
        }

        return Persistence.createEntityManagerFactory("simple-persistence-unit", configOverrides);
    }
}

