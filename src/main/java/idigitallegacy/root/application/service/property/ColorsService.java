package idigitallegacy.root.application.service.property;

import idigitallegacy.root.application.entity.ColorsEntity;
import idigitallegacy.root.application.service.property.IPropertyService;
import idigitallegacy.root.application.wrapper.ColorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorsService implements IPropertyService<ColorsEntity> {
    @Autowired
    private ColorsRepository colorsRepository;

    @Override
    public List<ColorsEntity> getAll() {
        return colorsRepository.findAll();
    }

    @Override
    public Optional<ColorsEntity> getById(Integer id) {
        return colorsRepository.findById(id);
    }

    @Override
    public Optional<ColorsEntity> getByName(String name) {
        return colorsRepository.findByBreedName(name);
    }

    @Override
    public void add(ColorsEntity entity) {
        colorsRepository.save(entity);
    }

    @Override
    public void removeById(Integer id) {
        colorsRepository.deleteById(id);
    }

    @Override
    public void removeByName(String name) {
        colorsRepository.removeBreedsEntitiesByBreedName(name);
    }
}

