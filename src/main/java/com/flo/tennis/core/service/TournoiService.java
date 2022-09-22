package com.flo.tennis.core.service;

import com.flo.tennis.core.HibernateUtil;
import com.flo.tennis.core.dto.TournoiDto;
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

    public void createTournoi(TournoiDto dto){
        Session session=null;
        Transaction tx =null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            Tournoi tournoi= new Tournoi();
            tournoi.setId(dto.getId());
            tournoi.setCode(dto.getCode());
            tournoi.setNom(dto.getNom());
            tournoiRepository.create(tournoi);
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

    public TournoiDto getTournoiById(Long id){
        Session session=null;
        Transaction tx =null;
        Tournoi tournoi=null;
        TournoiDto dto=null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            tournoi=tournoiRepository.getById(id);
            dto=new TournoiDto();
            dto.setId(tournoi.getId());
            dto.setCode(tournoi.getCode());
            dto.setNom(tournoi.getNom());
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

        return dto;
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
