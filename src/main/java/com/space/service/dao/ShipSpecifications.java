package com.space.service.dao;

import com.space.model.Ship;
import com.space.model.ShipType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ShipSpecifications {

    public static Specification<Ship> containsName(String name) {
        if (name != null && !name.isEmpty())
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("name"), "%" + name + "%");
            }
        };
        return null;
    }

    public static Specification<Ship> containsPlanet(String planet) {
        if (planet == null || planet.isEmpty())
            return null;
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("planet"), "%" + planet + "%");
            }
        };
    }
    public static Specification<Ship> equalsType(String shipType) {
        if (shipType == null || shipType.isEmpty())
            return null;
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("shipType"), shipType);
            }
        };
    }
    public static Specification<Ship> afterDate(Long after) {
        if (after == null || after == 0)
            return null;
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThan(root.<Date>get("prodDate"), new Date(after));
            }
        };
    }
    public static Specification<Ship> beforeDate(Long before) {
        if (before == null || before == 0)
            return null;
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThan(root.<Date>get("prodDate"), new Date(before));
            }
        };
    }
    public static Specification<Ship> isUsed(Boolean isUsed) {
        if (isUsed == null)
            return null;
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<Boolean>get("isUsed"), isUsed);
            }
        };
    }
    public static Specification<Ship> greaterThanSpeed(Double speed) {
        if (speed == null)
            return null;
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Double>get("speed"), speed);
            }
        };
    }
    public static Specification<Ship> lessThanSpeed(Double speed) {
        if (speed == null)
            return null;
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Double>get("speed"), speed);
            }
        };
    }
    public static Specification<Ship> greaterThanCrewSize(Integer crewSize) {
        if (crewSize == null)
            return null;
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Integer>get("crewSize"), crewSize);
            }
        };
    }
    public static Specification<Ship> lessThanCrewSize(Integer crewSize) {
        if (crewSize == null)
            return null;
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Integer>get("crewSize"), crewSize);
            }
        };
    }

    public static Specification<Ship> greaterThanRating(Double rating) {
        if (rating == null)
            return null;
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Double>get("rating"), rating);
            }
        };
    }
    public static Specification<Ship> lessThanRating(Double rating) {
        if (rating == null)
            return null;
        return new Specification<Ship>() {
            @Override
            public Predicate toPredicate(Root<Ship> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Double>get("rating"), rating);
            }
        };
    }

    public static Specification<Ship> collect(String name,
                                String planet,
                                ShipType shipType,
                                Long after,
                                Long before,
                                Boolean isUsed,
                                Double minSpeed,
                                Double maxSpeed,
                                Integer minCrewSize,
                                Integer maxCrewSize,
                                Double minRating,
                                Double maxRating){
        List<Specification<Ship>>  specs = new ArrayList<>();
        Specification<Ship> spec = null;
        if ((spec = ShipSpecifications.containsName(name)) != null)
            specs.add(spec);
        if ((spec = ShipSpecifications.containsPlanet(planet)) != null)
            specs.add(spec);
        if (shipType != null && (spec = ShipSpecifications.equalsType(shipType.toString())) != null)
            specs.add(spec);
        if ((spec = ShipSpecifications.afterDate(after)) != null)
            specs.add(spec);
        if ((spec = ShipSpecifications.beforeDate(before)) != null)
            specs.add(spec);
        if ((spec = ShipSpecifications.isUsed(isUsed)) != null)
            specs.add(spec);
        if ((spec = ShipSpecifications.greaterThanSpeed(minSpeed)) != null)
            specs.add(spec);
        if ((spec = ShipSpecifications.lessThanSpeed(maxSpeed)) != null)
            specs.add(spec);
        if ((spec = ShipSpecifications.greaterThanCrewSize(minCrewSize)) != null)
            specs.add(spec);
        if ((spec = ShipSpecifications.lessThanCrewSize(maxCrewSize)) != null)
            specs.add(spec);
        if ((spec = ShipSpecifications.greaterThanRating(minRating)) != null)
            specs.add(spec);
        if ((spec = ShipSpecifications.lessThanRating(maxRating)) != null)
            specs.add(spec);
        if (specs.size() > 0)
            spec = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            spec = Specification.where(spec).and(specs.get(i));
        }
        return spec;
    }
}
