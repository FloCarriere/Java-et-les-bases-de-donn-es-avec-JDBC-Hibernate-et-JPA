package com.flo.tennis.core.dto;

import com.flo.tennis.core.entity.Joueur;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class MatchDto {

    private Long id;
    private JoueurDto vainqueur;
    private JoueurDto finaliste;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JoueurDto getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(JoueurDto vainqueur) {
        this.vainqueur = vainqueur;
    }

    public JoueurDto getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(JoueurDto finaliste) {
        this.finaliste = finaliste;
    }
}
