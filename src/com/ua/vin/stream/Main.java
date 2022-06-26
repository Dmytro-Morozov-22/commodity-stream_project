package com.ua.vin.stream;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void menu() {
		System.out.println("1 - додати товар");
		System.out.println("2 - видалити товар");
		System.out.println("3 - замінити товар");
		System.out.println("4 - сортувати за назвою");
		System.out.println("5 - сортувати за довжиною");
		System.out.println("6 - сортувати за шириною");
		System.out.println("7 - сортувати за вагою");
		System.out.println("8 - вивести певний елемент");
		System.out.println("9 - вийти з програми");
	}

	static ArrayList<Commodity> store = new ArrayList<>();

	public static void main(String[] args) {

		while (true) {
			menu();
			Scanner sc = new Scanner(System.in);
			switch (sc.next()) {

//додати товар
			case "1":
				addItem();
				getInfo();
				break;

//видалити товар
			case "2":
				removeItem();
				getInfo();
				break;

//замінити товар
			case "3":
				replaceItem();
				break;

//сортувати за назвою
			case "4":
				sortByName();
				break;

//сортувати за довжиною
			case "5":
				sortByLength();
				break;

//сортувати за шириною
			case "6":
				sortByWidth();
				break;

//сортувати за вагою
			case "7":
				sortByWeight();
				break;

//вивести певний елемент
			case "8":
				getByIndex();
				break;

//вийти з програми
			case "9":
				exitTheProgram();
				break;

			case "sos":
				sc.close();
				break;
				
			default:
				System.err.println("Введіть числа від 1 до 9");
				break;
			}// switch

		} // while

	}// main

//МЕТОДИ:
//Отримати інформацію	
	static void getInfo() {
		store.stream().forEach(x -> System.out.println(x));
	}

//1 Додати товар	
	static void addItem() {
		store.add(new Commodity(getRandString(), getRandRange(30, 120),
				getRandRange(30, 120), getRandRange(30, 120)));
	}

//2 Видалити товар
	static void removeItem() {

		isEmptyStore();
		store.stream().findFirst().map(x -> {
			store.remove(0);
			System.out.println("Товар під індексом 0 видалено");
			return null;
		});
	}

//3 Замінити товар	
	static void replaceItem() {

		isEmptyStore();
		store.stream().findFirst().map(x -> {
			removeItem();
			addItem();
			getInfo();
			return null;
		});
	}

//4 Сортувати за назвою
	static void sortByName() {
		isEmptyStore();
		store.stream().sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
				.forEach(x -> System.out.println(x));
	}

//5 Сортувати за довжиною
	static void sortByLength() {
		isEmptyStore();
		store.stream().sorted((o1, o2) -> o1.getLength() - o2.getLength())
				.forEach(x -> System.out.println(x));
	}

//6 Сортувати за шириною	
	static void sortByWidth() {

		isEmptyStore();
		store.stream().sorted((o1, o2) -> o1.getWidth() - o2.getWidth())
				.forEach(x -> System.out.println(x));
	}

//7 Сортувати за вагою
	static void sortByWeight() {
		isEmptyStore();
		store.stream().sorted((o1, o2) -> o1.getWeight() - o2.getWeight())
				.forEach(x -> System.out.println(x));
	}

//8 Виводимо і-й елемент колекції(який вводимо з консолі(використовуємо Scanner))	
	static void getByIndex() {
		if (store.isEmpty()) {
			System.err.println("Додайте спочатку товар");
		} else {
			Scanner sc = new Scanner(System.in);

			if (sc.hasNextInt()) {
				int obtainedValue = sc.nextInt();
				if (store.size() >= obtainedValue) {
					System.out.println(store.get(obtainedValue));
				} else {
					System.out.println("Товару з таким індексом немає");
				}
			} else {
				sc.close();
			}
		}
	}

//9 Вийти з програми
	static void exitTheProgram() {
		System.out.println("Програму завершено!");
		System.exit(0);
	}

	static void isEmptyStore() {
		if (store.isEmpty())
			System.err.println("Додайте спочатку товар");
	}

//RANDOM METHODS:	
	// Random string
	private static String getRandString() {
		String[] itemList = { "Валіза", "Стіл", "Шафа", "Телевізор", "Крісло",
				"Диван", "Дзеркало", "Килим", "Велосипед", "Полиця", "Праска" };
		return itemList[getRandRange(0, itemList.length - 1)];
	}// RandString

	// Random range
	private static int getRandRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException(
					"min value must be smaller than max value");
		}

		Random r = new Random();
		return r.nextInt(max - min + 1) + min;
	}// RandRange

}// Main