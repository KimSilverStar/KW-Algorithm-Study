package Week4.N1493;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var st = new StringTokenizer(br.readLine());
        var length = parseInt(st.nextToken());
        var width = parseInt(st.nextToken());
        var height = parseInt(st.nextToken());
        var space = new Cube(length, width, height);
        N = parseInt(br.readLine());
        var cubes = new ArrayList<Cube>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            var size = parseInt(st.nextToken());
            var count = parseInt(st.nextToken());
            cubes.add(new Cube((int) pow(2, size), count));
        }

        var beforeCube = cubes.get(N - 1);
        long cubeCount = 0;
        long totalCount = 0;

        for (int i = N - 1; i >= 0; i--) {
            var cube = cubes.get(i);
            var sizeTerm = (long) Math.pow(beforeCube.height / cube.height, 3);
            cubeCount *= sizeTerm; //있던 큐브 분할
            var availableCubeCount = Math.min(space.divideCount(cube) - cubeCount, cube.count);
            cubeCount += availableCubeCount;
            totalCount += availableCubeCount;

            beforeCube = cube;
        }
        if (cubeCount * beforeCube.volume == space.volume)
            System.out.println(totalCount);
        else
            System.out.println("-1");
    }
}

class Cube {

    int length, width, height, count;
    long volume;

    public Cube(int size, int count) {
        this.length = size;
        this.width = size;
        this.height = size;
        this.count = count;
        this.volume = (long) pow(size, 3);
    }

    public Cube(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.volume = (long) length * width * height;
    }

    public long divideCount(Cube cube) {
        return (long) (this.length / cube.length) * (this.width / cube.width) * (this.height
            / cube.height);
    }

}
