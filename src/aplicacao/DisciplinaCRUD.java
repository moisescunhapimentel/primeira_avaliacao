package aplicacao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dominio.Disciplina;

public class DisciplinaCRUD {

	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;

	public void inicializarConexao() {
		entityManagerFactory = Persistence.createEntityManagerFactory("exemplo-jpa");
		entityManager = entityManagerFactory.createEntityManager();
	}

	public EntityTransaction obterTransacao() {
		return entityManager.getTransaction();
	}

	public void fecharConecoesEntity() {
		entityManager.close();
		entityManagerFactory.close();
	}

	public List<Disciplina> listarDisciplinas() {
		return entityManager.createQuery("SELECT d FROM Disciplina d", Disciplina.class).getResultList();
	}

	public Disciplina buscarDisciplinaPorId(Integer id) {
		return entityManager.find(Disciplina.class, id);
	}

	public void cadastrarDisciplina(Disciplina disciplina) {
		obterTransacao().begin();
		entityManager.persist(disciplina);
		obterTransacao().commit();
	}

	public void deletarDisciplina(Disciplina disciplina) {
		obterTransacao().begin();
		entityManager.remove(disciplina);
		obterTransacao().commit();
	}
}
