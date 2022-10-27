package it.jdk.ship.entities;


import javax.persistence.*;

@Entity
@Table(name = "cabin")
@SequenceGenerator(name = "cabin_generator", sequenceName = "cabin_sequence",
        initialValue = 1, allocationSize = 3)
public class Cabin {

    private Integer id;
    private String name;
    private Integer deckLevel;
    private Integer backCount;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "cabin_generator", strategy = GenerationType.SEQUENCE)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "deck_level")
    public Integer getDeckLevel() {
        return deckLevel;
    }

    public void setDeckLevel(Integer deckLevel) {
        this.deckLevel = deckLevel;
    }

    @Column(name = "bed_count")
    public Integer getBackCount() {
        return backCount;
    }

    public void setBackCount(Integer backCount) {
        this.backCount = backCount;
    }
}
