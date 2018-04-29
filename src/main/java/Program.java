import pl.sda.poznan.model.Category;
import pl.sda.poznan.model.Product;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("UsersDB");
        EntityManager entityManager = factory.createEntityManager();
        seedData(entityManager);

        //get all products

        Query query = entityManager.createQuery("select p from Product p");
        List resultList = query.getResultList();

        resultList.stream().forEach(s -> System.out.println(s));

        // Find by id
        Query getByIdQuery = entityManager.createQuery("select p from Product p where p.id = :productId");
        getByIdQuery.setParameter("productId", 2L);
        Product singleResult = (Product) getByIdQuery.getSingleResult();

        //Get max price
        Query maxPrice = entityManager.createQuery("select max (p.price) from Product p");
        Object singleResultMax = maxPrice.getSingleResult();

        Query query1 = entityManager.createQuery("select price, name from Product");
        List resultList1 = query1.getResultList();

        Query query2 = entityManager.createQuery("select p from Product p join p.category");
        List resultList2 = query1.getResultList();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();

        Root<Product> productRoot = criteriaQuery.from(Product.class);
        criteriaQuery.select(productRoot);
        criteriaQuery.where(criteriaBuilder.equal(productRoot.get("id"), criteriaBuilder.parameter(Long.class, "productId")));
        Query query3 = entityManager.createQuery(criteriaQuery);
        query3.setParameter("productId", 2L);
        Object singleResult2 = query3.getResultList();

        factory.close();
    }

    private static void seedData(EntityManager entityManager) {
        // todo 1 - kategoria
        Category category = new Category();
        category.setName("Laptops");

        //todo 2 - 3 produkty
        Product sony = new Product();
        sony.setName("SONY VAIO");

        Product msi = new Product();
        msi.setName("MSI");

        Product dell = new Product();
        dell.setName("dell");

        //todo 3- ustawienie kategorii w produktach
        sony.setCategory(category);
        msi.setCategory(category);
        dell.setCategory(category);

        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.persist(msi);
        entityManager.persist(sony);
        entityManager.persist(dell);

        entityManager.getTransaction().commit();
    }

}
