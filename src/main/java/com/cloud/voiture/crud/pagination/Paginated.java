package com.cloud.voiture.crud.pagination;
import java.util.List;
public class Paginated<T> {
    List<T> items;
    int totalPage;
    int nbPage;

    
    public Paginated(List<T> items, int totalPage, int nbPage) {
        setItems(items);
        setTotalPage(totalPage);
        setNbPage(nbPage);
    }
    public List<T> getItems() {
        return items;
    }
    public void setItems(List<T> items) {
        this.items = items;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getNbPage() {
        return nbPage;
    }
    public void setNbPage(int nbPage) {
        this.nbPage = nbPage;
    }
}
