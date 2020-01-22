package com.space.service;

import com.space.model.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class ShipServiceImpl implements ShipService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ShipRepository shipRepository;

    public ShipServiceImpl(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public Optional<Ship> findShipById(Long id) {
        return shipRepository.findById(id);
    }

    @Override
    public List<Ship> findAllShips() {
        return shipRepository.findAll();
    }

    @Override
    public Page<Ship> findAllShips(Specification<Ship> spec, Pageable page) {
        return shipRepository.findAll(spec, page);
    }

    @Override
    public List<Ship> findAllShips(Specification<Ship> spec) {
        return shipRepository.findAll(spec);
    }

    @Override
    public Long getCount() {
        return shipRepository.count();
    }

    @Override
    public List<Ship> findAllShips(Sort sort) {
        return shipRepository.findAll(sort);
    }

    @Override
    public List<Ship> findAllShips(Example<Ship> example, Sort sort) {
        return shipRepository.findAll(example, sort);
    }

    @Override
    public void addShip(Ship ship) {
        shipRepository.save(ship);
    }

    @Override
    public void updateShip(Ship ship) {
        shipRepository.save(ship);
    }

    @Override
    public void deleteShip(Long id) {
        shipRepository.deleteById(id);
    }
}
