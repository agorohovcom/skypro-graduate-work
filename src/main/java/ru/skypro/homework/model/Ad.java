package ru.skypro.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "ads")
@Data
@Schema(description = "Сущность объявления")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID объявления")
    private Long pk;

    @Schema(description = "Заголовок объявления")
    private String title;

    @Schema(description = "Цена объявления")
    private Integer price;

    @Schema(description = "Ссылка на картинку объявления")
    private String image;


    // todo уточнить, нужно ли поле именно User,
    //  потому что в openapi.yaml так:
    //  "type: integer
    //          format: int64
    //          description: 'id автора объявления'"
    @ManyToOne
    @JoinColumn(name = "author_id")
    @Schema(description = "ID автора объявления")
    private User author;

    // todo уточнить нужна ли эта связь, в файле openapi.yaml её нет
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    @Schema(description = "Список комментариев к объявлению")
    private List<Comment> comments;
}