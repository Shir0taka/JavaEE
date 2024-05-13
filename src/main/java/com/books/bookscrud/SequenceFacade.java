    package com.books.bookscrud;

    import com.books.bookscrud.entities.Sequence;
    import jakarta.ejb.Stateless;
    import jakarta.persistence.EntityManager;
    import jakarta.persistence.PersistenceContext;
    import jakarta.persistence.TypedQuery;
    import java.util.List;

    @Stateless
    public class SequenceFacade {

        @PersistenceContext(unitName = "default")
        private EntityManager em;

        public void create(Sequence sequence) {
            em.persist(sequence);
        }

        public void edit(Sequence sequence) {
            em.merge(sequence);
        }

        public void remove(Long sequenceId) {
            Sequence sequence = em.find(Sequence.class, sequenceId);
            if (sequence != null) {
                em.remove(sequence);
            }
        }

        public Sequence find(Long sequenceId) {
            return em.find(Sequence.class, sequenceId);
        }

        public List<Sequence> findAll() {
            TypedQuery<Sequence> query = em.createQuery("SELECT s FROM Sequence s", Sequence.class);
            return query.getResultList();
        }
    }