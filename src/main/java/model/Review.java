package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import model.abstr.AbstractEntity;

import java.util.Objects;

@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "reviews")
public class Review extends AbstractEntity {
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;
    @NotNull
    @Column(name = "rating", nullable = false)
    private Long rating;
    public Review(@NotNull String title, @NotNull String description,
                  @NotNull Long rating) {
        this.title = title;
        this.description = description;
        this.rating = rating;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return Objects.equals(title, review.title) &&
                Objects.equals(description, review.description) &&
                Objects.equals(rating, review.rating);
    }
    @Override
    public int hashCode() {
        return Objects.hash(title, description, rating);
    }
}
