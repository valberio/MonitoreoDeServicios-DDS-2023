package server;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
import io.github.flbulgarelli.jpa.extras.perthread.PerThreadEntityManagerProperties;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.HttpStatus;
import io.javalin.rendering.JavalinRenderer;
import server.handlers.AppHandlers;
import server.middlewares.AuthMiddleware;
import server.middlewares.SessionMiddleware;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import org.hibernate.cfg.Configuration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public class Server {
    
    private static Javalin app=null;

    private static EntityManagerFactory entityManagerFactory = null;
    
    public static Javalin app(){
        if(app == null)
            throw new RuntimeException("App no inicializada");
        return app;
    }
    
    public static void init(){
        if(app==null){

            String strport = System.getenv("PORT");
            if (strport == null){
                strport = "8089";
            }
            Integer port = Integer.parseInt(strport);

            if(entityManagerFactory == null) {

                Configuration configuration = new Configuration();
                configuration.setProperty("javax.persistence.jdbc.url", "jdbc:postgres://dpg-cll2k16aov6s73f1ne0g-a.oregon-postgres.render.com/dbtpama");
                configuration.setProperty("javax.persistence.jdbc.username", "dbtpamama_user");
                configuration.setProperty("javax.persistence.jdbc.password", "RZj4bqi4JsDsm7nPla8I9XymHqx6oZrd");

                // Otras configuraciones de Hibernate si es necesario

                entityManagerFactory = Persistence.createEntityManagerFactory("simple-persistence-unit", configuration.getProperties());
            }

            app = Javalin.create(config()).start(port);
            initTemplateEngine();
            AppHandlers.applyHandlers(app);
            Router.init();
            //Server.configureEntityManagerProperties();
            //Initializer.init();
        }
    }

    private static Consumer<JavalinConfig> config() {
        return config -> {
            config.staticFiles.add(staticFiles -> {
                staticFiles.hostedPath = "/";
                staticFiles.directory = "/assets";
            });
            AuthMiddleware.apply(config);
            SessionMiddleware.apply(config);
        };
    }

    private static  void initTemplateEngine() {
        JavalinRenderer.register(
                (path, model, context) -> { // Función que renderiza el template
                    Handlebars handlebars = new Handlebars();
                    handlebars.registerHelper("formateo", new Helper<LocalDateTime>() {
                        @Override
                        public CharSequence apply(LocalDateTime fecha, Options options) {
                            if (fecha == null) return "";

                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");
                            return fecha.format(formatter);
                        }
                    });
                    Template template = null;
                    try {
                        template = handlebars.compile(
                                "templates/" + path.replace(".hbs",""));
                        return template.apply(model);
                    } catch (IOException e) {
                        e.printStackTrace();
                        context.status(HttpStatus.NOT_FOUND);
                        return "No se encuentra la página indicada...";
                    }
                }, ".hbs" // Extensión del archivo de template
        );
    }
    public static EntityManager createEntityManager() {
        if (entityManagerFactory == null) {
            throw new RuntimeException("EntityManagerFactory no inicializado");
        }
        return entityManagerFactory.createEntityManager();
    }

}

