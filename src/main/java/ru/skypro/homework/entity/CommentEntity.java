package ru.skypro.homework.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;


@Entity
@Table(name = "comments")
@Getter
@Setter
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    @CreationTimestamp
    private LocalDate createdAt;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id")
    private AdEntity ad;

}