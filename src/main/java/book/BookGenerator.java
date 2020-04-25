package book;

import book.model.Book;
import com.github.javafaker.Faker;

import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BookGenerator {

    private static Faker faker = new Faker(new Locale("en"));

    public static Book create(){

        Date date = faker.date().past(182500, TimeUnit.DAYS);
        java.time.LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        /*
        Ha az alábbi módon próbáltam az "available"-nek értéket adni: ".available(faker.bool())"
        ezt a hibát adta: "Error:(28, 38) java: incompatible types: com.github.javafaker.Bool cannot be converted to boolean"
        Ezért ezt másképp oldottam meg.
         */
        Random random = new Random();
        Boolean ava= random.nextBoolean();

        Book book = Book.builder()
                .isbn13(faker.code().isbn13())
                .author(faker.book().author())
                .title(faker.book().title())
                .format(faker.options().option(Book.Format.class))
                .publisher(faker.book().publisher())
                .publicationDate(localDate)
                .pages(faker.number().numberBetween(1,1000))
                .available(ava)
                .build();
        return book;
    }
}
