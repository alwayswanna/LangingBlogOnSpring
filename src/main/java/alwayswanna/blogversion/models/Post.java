package alwayswanna.blogversion.models;


import javax.persistence.*;

@Entity
public class Post {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "post_id", updatable = false, nullable = false)
        private Integer id;




        @Column(name = "post_title", nullable = false)
        private String title;
        @Column(name = "post_anons", nullable = false)
        private String anons;
        @Column(name = "post_fulltext", nullable = false)
        private String fulltext;
        @Column(name = "post_views", nullable = false)
        private int views;
        @Column(name = "filename", nullable = false)
        private String filename;




        //Getters and Setters


        public String getFilename() {
                return filename;
        }

        public void setFilename(String filename) {
                this.filename = filename;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getAnons() {
                return anons;
        }

        public void setAnons(String anons) {
                this.anons = anons;
        }

        public String getFulltext() {
                return fulltext;
        }

        public void setFulltext(String fulltext) {
                this.fulltext = fulltext;
        }

        public Post(String title, String anons, String fulltext) {
                this.title = title;
                this.anons = anons;
                this.fulltext = fulltext;
        }

        public Post() {
        }
}
