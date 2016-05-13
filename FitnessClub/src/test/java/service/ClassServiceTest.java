package service;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.kpfu.itis.aygul.model.ClassEntity;
import ru.kpfu.itis.aygul.repository.ClassRepository;
import ru.kpfu.itis.aygul.service.ClassServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aygulmardanova on 12.05.16.
 */
public class ClassServiceTest {

    private static ClassEntity classEntity;

    private static List<ClassEntity> classes;

    private static ClassServiceImpl classService;

    private static ClassRepository classRepository;

    private static final String incor_name = "aaa";
    private static final int incor_id = 1000;


    @BeforeClass
    public static void beforeMethod() {
        classEntity = new ClassEntity();
        classEntity.setName("yoga");
        classEntity.setDescription("Some description");

        classes = new ArrayList<>();
        classes.add(classEntity);

        classRepository = mock(ClassRepository.class);

        when(classRepository.findAll()).thenReturn(classes);

        when(classRepository.findById(anyInt())).thenReturn(null);
        when(classRepository.findById(classEntity.getId())).thenReturn(classEntity);

        when(classRepository.findByName(anyString())).thenReturn(null);
        when(classRepository.findByName(classEntity.getName())).thenReturn(classEntity);

        classService = new ClassServiceImpl();
        classService.setClassRepository(classRepository);
    }

    @Test
    public void getAllShouldReturnCorrectList() {
        Assert.assertEquals(classes, classService.getAll());
    }

    @Test
    public void getClassByIdShouldReturnClassIfCorrectId() {
        Assert.assertEquals(classEntity, classService.getClassById(classEntity.getId()));
    }

    @Test
    public void getClassByIdShouldReturnNullIfIncorrectId() {
        Assert.assertNull(classService.getClassById(incor_id));
    }

    @Test
    public void getClassByNameShouldReturnClassIfCorrectName() {
        Assert.assertEquals(classEntity, classService.getClassByName(classEntity.getName()));
    }

    @Test
    public void getClassByNameShouldReturnNullIfIncorrectName() {
        Assert.assertNull(classService.getClassByName(incor_name));
    }

    @Test
    public void ifClassNameExistsShouldReturnTrueIfNameExists() {
        Assert.assertTrue(classService.ifClassNameExists(classEntity.getName()));
    }

    @Test
    public void ifClassNameExistsShouldReturnFalseIfNonExistingName() {
        Assert.assertFalse(classService.ifClassNameExists(incor_name));
    }

}
