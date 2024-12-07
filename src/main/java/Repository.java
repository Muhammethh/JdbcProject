import java.util.List;

public interface Repository <S, U> {

        void createTable();

        void save(S entity); //varlık demek entity

        List<S> findAll();

        void update(S entity);

        void deleteById(U id); //id verilen ögrenciyi silme

        S findById(U id);


}
