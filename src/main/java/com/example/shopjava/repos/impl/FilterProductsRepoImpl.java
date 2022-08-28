package com.example.shopjava.repos.impl;

import com.example.shopjava.entities.product.Laptop;
import com.example.shopjava.entities.product.Phone;
import com.example.shopjava.entities.product.Watch;
import com.example.shopjava.repos.FilterProductsRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Repository
public class FilterProductsRepoImpl implements FilterProductsRepo {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger log = LoggerFactory.getLogger(FilterProductsRepoImpl.class);

    @Override
    public List<Phone> filterPhones(Set<String> filters, Map<String, List<String>> fullFilters, Integer min, Integer max) {
        String query = generateQuery("Phone", filters, fullFilters, min, max);
        log.info(query);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Laptop> filterLaptops(Set<String> filters, Map<String, List<String>> fullFilters, Integer min, Integer max) {
        String query = generateQuery("Laptop", filters, fullFilters, min, max);
        log.info(query);

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Watch> filterWatches(Set<String> filters, Map<String, List<String>> fullFilters, Integer min, Integer max) {
        String query = generateQuery("Watch", filters, fullFilters, min, max);
        log.info(query);

        return entityManager.createQuery(query).getResultList();
    }

    private String generateQuery(String table, Set<String> filters, Map<String, List<String>> fullFilters,
                                 Integer min, Integer max) {
        String temp, init, query = init = temp = "select p from " + table + " p where (p.price between " + min + " and " + max + ") and (";
        if (filters != null) {
            Map<String, Boolean> checkedFilters = new HashMap<>();
            for (String filter : filters) {
                checkedFilters.put(filter, false);
            }
            for (Map.Entry<String, List<String>> entry : fullFilters.entrySet()) {
                List<String> list = entry.getValue();
                String key = entry.getKey().toLowerCase().replace('-', '_');
                key = key.replace(' ', '_');
                for (String filter : filters) {
                    if(filter.contains("present") && !checkedFilters.get(filter)){

                        String[] arr1 = query.split(" ");
                        if(!arr1[arr1.length-1].equals("(")){
                            String[] arr2 = Arrays.copyOfRange(arr1, 0, arr1.length - 1);
                            query = String.join(" ", arr2) + ") and (";
                            temp = query;
                        }

                        String[] filterSplit = filter.split(" ");
                        String obj = "";
                        if(filterSplit[filterSplit.length-1].equals("present") && filterSplit[filterSplit.length-2].equals("doesn't")){
                            for (int i = 0; i < filterSplit.length - 2; i++) {
                                obj += filterSplit[i] + "_";
                            }
                            obj = obj.substring(0, obj.length()-1).toLowerCase();
                            query += "p." + obj + "=false and";
                        }
                        else {
                            for (int i = 0; i < filterSplit.length - 1; i++) {
                                obj += filterSplit[i] + "_";
                            }
                            obj = obj.substring(0, obj.length()-1).toLowerCase();
                            query += "p." + obj + "=true and";
                        }
                        checkedFilters.put(filter, true);
                        break;
                    }
                    for (String s : list) {
                        if (filter.equals(s) && !filter.contains("present")) {
                            query += " p." + key + "='" + filter + "' or";
                            log.info(query);
                            break;
                        }
                    }
                }
                if (!init.equals(query) && !temp.equals(query)) {
                    String[] arr1 = query.split(" ");
                    String[] arr2 = Arrays.copyOfRange(arr1, 0, arr1.length - 1);
                    query = String.join(" ", arr2) + ") and (";
                    temp = query;
                    log.info(query);
                }
            }
        }
        if (init.equals(query)) {
            query = "select p from " + table + " p where p.price between " + min + " and " + max;
        } else {
            String[] arr1 = query.split(" ");
            String[] arr2 = Arrays.copyOfRange(arr1, 0, arr1.length - 2);
            query = String.join(" ", arr2);
        }
        return query;
    }
}
