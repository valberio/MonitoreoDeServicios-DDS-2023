package server;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static EntityManagerFactory entityManagerFactory;


    public static void main(String[] args) throws Exception {
        
        entityManagerFactory =  createEntityManagerFactory();

        Server.init();
    }

    public static EntityManagerFactory createEntityManagerFactory() throws Exception {
        // https://stackoverflow.com/questions/8836834/read-environment-variables-in-persistence-xml-file
        Map<String, String> env = System.getenv();
        Map<String, Object> configOverrides = new HashMap<String, Object>();

        String[] keys = new String[] { "javax.persistence.jdbc.url", "javax.persistence.jdbc.user",
                "javax.persistence.jdbc.password", "javax.persistence.jdbc.driver", "hibernate.hbm2ddl.auto",
                "hibernate.dialect"};

        for (String key : keys) {
            if (env.containsKey(key)) {

                String value = env.get(key);
                configOverrides.put(key, value);

            }
        }

        return Persistence.createEntityManagerFactory("simple-persistence-unit", configOverrides);
    }
}

