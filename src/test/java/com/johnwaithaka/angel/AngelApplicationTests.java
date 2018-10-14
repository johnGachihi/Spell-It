package com.johnwaithaka.angel;

import com.johnwaithaka.angel.entities.Lesson;
import com.johnwaithaka.angel.entities.Level;
import com.johnwaithaka.angel.entities.Word;
import com.johnwaithaka.angel.models.MenuItem;
import com.johnwaithaka.angel.repositories.AdminRepository;
import com.johnwaithaka.angel.repositories.AngelRepository;
import com.johnwaithaka.angel.repositories.LevelRepository;
import static org.junit.Assert.assertArrayEquals;

import com.johnwaithaka.angel.repositories.PhoneticRepository;
import com.johnwaithaka.angel.services.AngelService;
import com.johnwaithaka.angel.services.FileService;
import com.johnwaithaka.angel.services.PhoneticService;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AngelApplicationTests {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private LevelRepository levelRepository;
    @Autowired
    private AngelRepository angelRepository;
    @Autowired
    private AngelService angelService;
    @MockBean
    private PhoneticRepository phoneticRepository;
    @InjectMocks
    private PhoneticService phoneticService;
    @Autowired
    private FileService fileService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testLevelsToMenuItems(){
        Word word = new Word(
                "Man",
                Arrays.asList("M", "an"),
                "image.png",
                "phonetic.mp3"
        );
        Lesson lesson = new Lesson(word);

        Level l = new Level();
        ObjectId objectId = new ObjectId();
        l.setId(objectId.toString());
        l.addLesson(lesson);

        List<Level> levels = new ArrayList<>(
                Arrays.asList(l, l)
        );
        List<MenuItem> actualMenuItems = MenuItem.levelsToMenuItems(levels);

        MenuItem lessonMenuItem = new MenuItem(
                "Man",
                l.getId(),
                objectId.toString()
        );
        List<MenuItem> exptectedMenuItems = new ArrayList<>(
                Arrays.asList(
                        new MenuItem(
                        "Level 1",
                        "",
                        Arrays.asList(lessonMenuItem),
                        objectId.toString()
                        ), new MenuItem(
                        "Level 2",
                        "",
                        Arrays.asList(lessonMenuItem),
                        objectId.toString()
                        )
                )
        );

        assertArrayEquals(exptectedMenuItems.toArray(), actualMenuItems.toArray());
    }

    @Test
    public void testCountUserByRegDate(){
        Assert.assertEquals(1, angelRepository.countByRegDate(11, new Date()));
    }

    @Test
    public void testGetRegistrationDateCountData() {
        List<Integer> expected = new ArrayList<Integer>(
                Arrays.asList(1,1,1,1,1,1,1,1,1,0,1,0)
        );

        List<Integer> actual = angelService.getRegistrationDateCountData();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetAllAbsentText(){
        PhoneticRepository phoneticRepository = mock(PhoneticRepository.class);
        when(phoneticRepository.existsByText("a")).thenReturn(true);
        when(phoneticRepository.existsByText("b")).thenReturn(true);
        when(phoneticRepository.existsByText("c")).thenReturn(true);

        PhoneticService phoneticService = new PhoneticService(phoneticRepository, fileService);

        List<String> actual = phoneticService.getAllAbsentText(
                Arrays.asList("a", "b", "c", "d", "e", "f", "g")
        );

        List<String> expected = Arrays.asList("d", "e", "f", "g");


        Assert.assertArrayEquals(expected.toArray(), actual.toArray());

    }
}
