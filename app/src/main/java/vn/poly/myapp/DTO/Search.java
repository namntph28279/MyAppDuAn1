package vn.poly.myapp.DTO;

public class Search {
    private int id;
    private String search;

    public Search(int id, String search) {
        this.id = id;
        this.search = search;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
