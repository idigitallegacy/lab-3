package idigitallegacy.root.application.service.property;

import idigitallegacy.root.application.entity.BreedsEntity;
import idigitallegacy.root.application.service.property.IPropertyService;
import idigitallegacy.root.application.wrapper.BreedsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreedsService implements IPropertyService<BreedsEntity> {
    @Autowired
    private BreedsRepository breedsRepository;

    @Override
    public List<BreedsEntity> getAll() {
        return breedsRepository.findAll();
    }

    @Override
    public Optional<BreedsEntity> getById(Integer id) {
        return breedsRepository.findById(id);
    }

    @Override
    public Optional<BreedsEntity> getByName(String name) {
        return breedsRepository.findByBreedName(name);
    }

    @Override
    public void add(BreedsEntity entity) {
        breedsRepository.save(entity);
    }

    @Override
    public void removeById(Integer id) {
        breedsRepository.deleteById(id);
    }

    @Override
    public void removeByName(String name) {
        breedsRepository.removeBreedsEntitiesByBreedName(name);
    }
}
