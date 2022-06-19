package com.blockbuster.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import com.blockbuster.entity.Employee;
import com.blockbuster.entity.Employee.EmploymentType;
import com.blockbuster.entity.Movie;

public class CustomShopRepositoryImpl extends GenericRepositoryImpl implements CustomShopRepository {

	@Override
	public Optional<Employee> findEmployeeByEmploymentTypeAndShopID(Employee employee) {
		try {
			return Optional.ofNullable(entityManagerFactory.createEntityManager()
					.createQuery("select e from Employee e where e.employmentType = :type and e.shop.id = :shopId",
							Employee.class)
					.setParameter("type", employee.getEmploymentType())
					.setParameter("shopId", employee.getShop().getId()).getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<Employee> findEmployeeByEmploymentTypeStaffAndShopID(Employee employee) {
		try {
			return Optional.ofNullable(entityManagerFactory.createEntityManager().createQuery(
					"select e from Employee e where e.employmentType = :type and e.shiftType = :shiftType and e.shop.id = :shopId",
					Employee.class).setParameter("type", employee.getEmploymentType())
					.setParameter("shiftType", employee.getShiftType())
					.setParameter("shopId", employee.getShop().getId()).getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Movie> findMoviesByShopID(int shopId) {
		return entityManagerFactory.createEntityManager()
				.createQuery("select m from Movie m where m.shop.id = :shopId", Movie.class)
				.setParameter("shopId", shopId).getResultList();
	}

	@Override
	public List<Movie> findAvailableMoviesByShopID(int shopId) {
		return entityManagerFactory.createEntityManager()
				.createQuery("select m from Movie m where m.shop.id = :shopId and m.inStock =:inStock", Movie.class)
				.setParameter("inStock", true).setParameter("shopId", shopId).getResultList();
	}

	@Override
	public List<Employee> findCounterStaffByShopID(int shopId) {
		return entityManagerFactory.createEntityManager()
				.createQuery("select e from Employee e where e.employmentType =:employmentType and e.shop.id=:shopId",
						Employee.class)
				.setParameter("employmentType", EmploymentType.COUNTER_STAFF).setParameter("shopId", shopId)
				.getResultList();
	}

}
