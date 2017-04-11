package utng.edu.mx.menusarahi.gallery;

/**
 * Created by jony on 02/03/17.
 */

public class ImageItem {

    private int id;
    private String description;


    public ImageItem(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public ImageItem(){
        this(0,"");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ImageItem{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
