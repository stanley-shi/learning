## Max Points on a Line

### Problem description: 
    Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
    
### Thoughts

    For each point a:
        For each other points b:
            calculate the angle of the line that cross both point (a, b);
            put this angle to a HashMap<Double, Integer>, the key is the angle and value is the count related with this angle; if the key already exist, increase the count; else put value as 1;
            
            
    return the max value of the above hash map;
