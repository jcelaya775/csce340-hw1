#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>

using namespace std;

int main() {
    queue<vector<int>> q;

    vector<int> temp = {3, 4, 5, 6};

    for (int i=0; i<temp.size(); i++) {
        vector<int> t;
        t.push_back(temp[i]);
        q.push(t);
    }

    while (!q.empty()) {
        vector<int> temp = q.front();

        for (int j=0; j<temp.size(); j++) {
            cout << temp[j] << " ";
        }

        cout << endl;

        q.pop();
    }
    
    return 0;
}