package app.model;

import app.entities.Point;
import org.springframework.stereotype.Component;

//Подсчет результата
//По сути вся полезная работа нашего backend'а
@Component
public class Graphic {
    private boolean isInArea(double x, double y, double r) {
        boolean triangle, square, sector;
        if(r >= 0) {
            //При положительном r

            //Треугольник
            triangle = x >= 0 && y <= 0 && y>=(x-r/2);
            //Квадрат
            square = x <= 0 && y >= 0 && x >= -r && y <= r/2;
            //Круг
            sector = x >= 0 && y >= 0 && Math.sqrt(x * x + y * y) <= r;
        }
        else{
            //При отрицательном r

            //Треугольник
            triangle = x <= 0 && y >= 0 && y<=(x-r/2);
            //Квадрат
            square = x >= 0 && y <= 0 && x <= Math.abs(r) && y >= -Math.abs(r) / 2;
            //Круг
            sector = x <= 0 && y <= 0 && Math.sqrt(x * x + y * y) <= Math.abs(r);
        }
        return triangle || square || sector;
    }

    //Преобразование Point в x, y, r
    public boolean isInArea(Point point) {
        return isInArea(point.getX(), point.getY(), point.getR());
    }

}
