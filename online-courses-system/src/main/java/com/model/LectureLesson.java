package com.model;

import java.util.ArrayList;
import java.util.List;

public class LectureLesson extends Lesson
{
    private List<LearningMaterial> materials = new ArrayList<>();// Список материалов

    public LectureLesson(String name_lession, String content) {
        super(name_lession, content, "лекция");
    }

    //добавление материала
    public void addMaterial(LearningMaterial material) {
        materials.add(material);
    }

    //удаление материала
    public void removeMaterial(LearningMaterial material) {
        materials.remove(material);
    }

     //контент лекции
    public void displayContent() {
        System.out.println("Лекция: " + getContent());

        for (LearningMaterial material : materials) {
          material.informationOutput();
        }
    }

    //геттеры
    public List<LearningMaterial> getMaterials() {
        return materials;
    }

}
