package rs.raf.demo;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.demo.repositories.category.CategoryRepository;
import rs.raf.demo.repositories.category.MySqlCategoryRepository;
import rs.raf.demo.repositories.comment.CommentRepository;
import rs.raf.demo.repositories.comment.MySqlCommentRepository;
import rs.raf.demo.repositories.news.MySqlNewsRepository;
import rs.raf.demo.repositories.news.NewsRepository;
import rs.raf.demo.repositories.newsTag.MySqlNewsTagRepository;
import rs.raf.demo.repositories.newsTag.NewsTagRepository;
import rs.raf.demo.repositories.tag.MySqlTagRepository;
import rs.raf.demo.repositories.tag.TagRepository;
import rs.raf.demo.repositories.user.MySqlUserRepository;
import rs.raf.demo.repositories.user.UserRepository;
import rs.raf.demo.services.*;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig { // ovaj ResourceConfig u pozadini nasledjuje Application, ali sadzri neke metode koje su nam potrebne

    public HelloApplication() {
        // Ukljucujemo validaciju da bi @Valid radio
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
//                this.bind(MySqlUserRepository.class).to(UserRepository.class).in(Singleton.class);
//                this.bindAsContract(UserService.class);

                this.bind(MySqlCategoryRepository.class).to(CategoryRepository.class).in(Singleton.class);
                this.bind(MySqlCommentRepository.class).to(CommentRepository.class).in(Singleton.class);
                this.bind(MySqlNewsRepository.class).to(NewsRepository.class).in(Singleton.class);
                this.bind(MySqlTagRepository.class).to(TagRepository.class).in(Singleton.class);
                this.bind(MySqlUserRepository.class).to(UserRepository.class).in(Singleton.class);
                this.bind(MySqlNewsTagRepository.class).to(NewsTagRepository.class).in(Singleton.class);

                this.bindAsContract(CategoryService.class);
                this.bindAsContract(CommentService.class);
                this.bindAsContract(NewsService.class);
                this.bindAsContract(TagService.class);
                this.bindAsContract(UserService.class);
                this.bindAsContract(NewsTagService.class);



            }
        };
        register(binder); // registrujemo kontejner iznad

        // Ucitavamo resurse da na bi dolazilo do nekih gresaka u ucitavanju resursa
        packages("rs.raf.demo.resources");
    }
}
