package ru.skypro.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comments")
@Data
@Schema(description = "Сущность комментария")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID комментария")
    private Long pk;

    @Schema(description = "Текст комментария")
    private String text;

    @Schema(description = "Дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970")
    private Long createdAt;

    // todo уточнить, нужно ли поле именно User,
    //  потому что в openapi.yaml так:
    //  "type: integer
    //          format: int64
    //          description: 'id автора комментария'"
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @Schema(description = "ID автора комментария")
    private User author;

    // todo уточнить нужна ли эта связь, в файле openapi.yaml её нет
    @ManyToOne
    @JoinColumn(name = "ad_id", nullable = false)
    @Schema(description = "Объявление, к которому оставлен комментарий")
    private Ad ad;

    @Transient
    @Schema(description = "Имя создателя комментария")
    private String authorFirstName;

    @Transient
    @Schema(description = "Ссылка на аватар автора комментария")
    private String authorImage;
}