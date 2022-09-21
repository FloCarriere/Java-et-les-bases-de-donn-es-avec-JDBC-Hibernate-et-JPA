package com.flo.tennis.core.service;

import com.flo.tennis.core.HibernateUtil;
import com.flo.tennis.core.entity.Joueur;
import com.flo.tennis.core.repository.JoueurRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class JoueurService {
    private JoueurRepositoryImpl joueurRepository;

    public JoueurService(){
        this.joueurRepository = new JoueurRepositoryImpl();
    }

    public void createJoueur(Joueur joueur){


        Session session=null;
        Transaction tx =null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            joueurRepository.create(joueur);
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

    public Joueur getJoueurById(Long id){

        Session session=null;
        Transaction tx =null;
        Joueur joueur=null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            joueur=joueurRepository.getById(id);
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
        return joueur;
    }

    public void renomme(Long id, String nouveauNom){
        Joueur joueur=getJoueurById(id);

        Session session=null;
        Transaction tx =null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            joueur.setNom(nouveauNom);
            session.merge(joueur);
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

    public void changerSexe(Long id, char sexe){
        Joueur joueur=getJoueurById(id);

        Session session=null;
        Transaction tx =null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            joueur.setSexe(sexe);
            session.merge(joueur);
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

    public void deleteJoueur(Long id){
        Session session=null;
        Transaction tx =null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx=session.beginTransaction();
            joueurRepository.delete(id);
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
