package racingcar.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacingGame {
	public static final String NAME_SEPARATION_REGEX = ",";
	private final Cars cars;
	private final CarNumberGenerator generator;

	public RacingGame(String names) {
		this.cars = new Cars(createCarNames(names));
		this.generator = new CarRandomNumberGenerator();
	}

	private List<Car> createCarNames(String names) {
		String[] carNames = names.split(NAME_SEPARATION_REGEX);
		List<Car> cars = new ArrayList<>();

		for (String carName : carNames) {
			cars.add(new Car(carName));
		}
		return cars;
	}

	public void run() {
		this.cars.move(this.generator);
	}

	public List<String> getWinners() {
		return this.cars.getWinners()
			.stream()
			.map(Car::getName)
			.map(CarName::toString)
			.collect(Collectors.toList());
	}

	public Map<String, Integer> getGameResult() {
		return this.cars
			.toList()
			.stream()
			.collect(Collectors.toMap(car -> car.getName().toString(), car -> car.getMileage().toInteger()));
	}
}
