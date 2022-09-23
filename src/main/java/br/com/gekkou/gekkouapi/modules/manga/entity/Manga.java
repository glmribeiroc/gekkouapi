package br.com.gekkou.gekkouapi.modules.manga.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String name;
    private String other_name;
    private String author;
    private String artit;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String release;
    private String sinopse;
    private String cover;
    private Date created_at = new Date();

    @ManyToMany
    @JoinTable(name = "manga_category", joinColumns = @JoinColumn(name = "manga_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @OneToMany(mappedBy = "manga")
    private List<Chapter> chapters;

    @OneToMany(mappedBy = "manga")
    private List<Review> reviews;
}
