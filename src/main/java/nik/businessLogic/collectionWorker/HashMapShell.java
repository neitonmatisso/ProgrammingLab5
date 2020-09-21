package nik.businessLogic.collectionWorker;

import nik.businessLogic.sourceClasses.Product;

import java.util.*;
import java.util.stream.Collectors;

public class HashMapShell implements CollectionShell {
    private HashMap<Integer, Product> productHashMap;
    private Date createDate;
    public HashMapShell(){
        productHashMap = new HashMap<>();
        createDate = new Date();
    }
    @Override
    public void addNewProduct(Product product) {
        productHashMap.put(product.getId(),product);
    }

    @Override
    public Product getById(Integer id) throws NullPointerException {
        Product returnProduct = productHashMap.get(id);
        if(returnProduct == null){
            throw new NullPointerException();
        }
            return returnProduct;
    }

    @Override
    public void removeProductById(Integer id) throws NullPointerException {
        Product returnProduct = productHashMap.get(id);
        if(returnProduct == null){
            throw new NullPointerException();
        }
        productHashMap.remove(id);
    }

    @Override
    public void clearAll() {
        productHashMap.clear();
    }

    @Override
    public List<Product> getProductList() {
        return new ArrayList<>(productHashMap.values());
    }

    @Override
    public void sort() {
        productHashMap.keySet().stream().sorted(Comparator.comparingInt(x -> x));
    }

    @Override
    public String getInfo() {
        return "размер коллекции: " +productHashMap.size() + " тип коллекции HashMap " + " дата создания "+  createDate;
    }

    @Override
    public boolean isEmpty() {
        return productHashMap.isEmpty();
    }
}
