#include <iostream>
#include <queue>
#include <algorithm>

using namespace std;

int findLIS(int sequence[], int n)
{
    int subLens[n]; // contains lengths of subsequences

    if (n >= 1)
      subLens[0] = 1;

    for (int i = 1; i < n; i++) {
      subLens[i] = 1;
      for (int j = 0; j < i; j++)
        if (sequence[i] > sequence[j] && subLens[i] < subLens[j] + 1)
          subLens[i] = subLens[j] + 1;
    }

    int maxSubLen = *max_element(subLens, subLens + n); // return value

    int subLen = maxSubLen;   
    queue<int*> lis;

    // initalize lis -> find max starting points
    for (int i=0; i<n; i++) 
    {
        if (subLens[i] == maxSubLen) 
        {
            int parent[maxSubLen] = {};
            parent[subLen-1] = sequence[i];
            
            lis.push(parent);
        }
    }

    subLen--;

    while (subLen > 0) 
    {
        int numParents = lis.size();

        while (numParents > 0) {
            int *parent = lis.front(); // current parent

            for (int i=0; i<n; i++) 
            {

                if (subLens[i] == subLen && sequence[i] < parent[subLen-1]) 
                {
                    // child 'inherits' parents elements
                    int child[maxSubLen] = {}; 
                    for (int j=0; j<maxSubLen; j++) {
                        child[j] = parent[j];
                    }

                    child[subLen-1] = sequence[i]; // fill in new value
                    lis.push(child);
                }
            }
            
            lis.pop(); // remove current parent
            numParents--;
        }

        subLen--;
    }

    // print out all lis
    while (!lis.empty()) 
    {
        int *sub = lis.front();

        for (int i=0; i<n; i++)
            cout << sub[i] << " ";
        cout << endl;

        lis.pop();
    }

    return maxSubLen;
}

int main()
{
    // int sequence[] = {68, 49, 31, 25, 22, 48, 38, 19,
    //                 1, 80, 8, 83, 55, 33, 98, 29};
    // const int n = sizeof(sequence) / sizeof(sequence[0]);

    int sequence[] = {10, 9, 2, 5, 3, 101, 7, 18};
    const int n = sizeof(sequence) / sizeof(sequence[0]);

    cout << findLIS(sequence, n);

    return 0;
}