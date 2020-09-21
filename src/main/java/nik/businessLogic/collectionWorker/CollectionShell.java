package nik.businessLogic.collectionWorker;

import nik.businessLogic.sourceClasses.Product;

import java.util.List;

/**
 * иетерейс для классов-оберток над коллекциями.
 * В случае чего можно будет спокойно перейти на любую другую коллекцию
 */
public interface CollectionShell {
    /**
     * добавить новую сущность в интерйфейс
     * @param product Ссылка на сущность
     */
    void addNewProduct(Product product);

    /**
     * Получение сущности из коллекции по айди
     * @param id айди сущности
     * @return возвращает ссылку на сущнность
     */
    Product getById(Integer id);

    /**
     * удаляет сущность по айди
     * @param id айди сущности
     */
    void removeProductById(Integer id);

    /**
     * очистка коллекции
     */
    void clearAll();

    /**
     * @return возвращает список всех сущностей
     */
    List<Product> getProductList();

    /**
     * сортировка коллекции
     */
    void sort();

    /**
     * Информация о коллекции
     * @return информацию в строковом представлении
     */
    String getInfo();

    boolean isEmpty();

}
