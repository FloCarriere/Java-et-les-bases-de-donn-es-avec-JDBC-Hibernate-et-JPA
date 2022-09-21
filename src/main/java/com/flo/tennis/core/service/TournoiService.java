package com.flo.tennis.core.service;

import com.flo.tennis.core.HibernateUtil;
import com.flo.tennis.core.entity.Joueur;
import com.flo.tennis.core.entity.Tournoi;
import com.flo.tennis.core.repository.JoueurRepositoryImpl;
import com.flo.tennis.core.repository.TournoiRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TournoiService {
    private TournoiRepositoryImpl tournoiRepository;

    public TournoiService(){
        this.tournoiRepository = new TournoiRepositoryImpl();
    }

    public void createTournoi(Tournoi tournoi){
        tournoiRepository.create(tournoi);
    }

    public Tournoi getTournoiById(Long id){
        return tournoiRepository.getById(id);
    }

    public void deleteTounoi(Long id){
        Session session=null;
        Transaction tx =null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            tournoiRepository.delete(id);
            tx.commit();

        }catch (Exception e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }

        finally {
            if(session != null){
                session.close();
            }

        }
    }
}
