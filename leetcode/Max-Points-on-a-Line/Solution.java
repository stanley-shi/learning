/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    public int maxPoints(Point[] points) {
        if(null==points||points.length==0)return 0;
        if(points.length==1)return 1;
        int max = 0;
		for (Point p : points) {
			HashMap<Double, Integer> values = new HashMap<Double, Integer>();
			int samePoints = 0;
			for (Point p2 : points) {
				if (p2 == p)
					continue;
				Double angle;
				if (p2.y == p.y) {
					if (p2.x != p.x)
						angle = Double.MAX_VALUE;
					else {
						samePoints += 1;
						continue;
					}
				} else
					angle = ((double) (p2.x - p.x)) / (p2.y - p.y);

				if (values.containsKey(angle)) {
					values.put(angle, values.get(angle) + 1);
				} else
					values.put(angle, 2);
			}

			for (int i : values.values()) {
				if (i + samePoints > max)
					max = i + samePoints;
			}
            if(max<samePoints+1) max=samePoints+1;
		}
		return max;
    }
}
