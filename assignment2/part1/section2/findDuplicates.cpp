#include <iostream>
#include <unordered_set>
#include <vector>
using namespace std;

bool hasDuplicate(const vector<int>& nums) {
    unordered_set<int> seen;
    for (int num : nums) {
        if (seen.find(num) != seen.end()) return true;
        seen.insert(num);
    }
    return false;
}

int main() {
    cout << boolalpha << hasDuplicate({1,2,3,4,1}) << "\n";
}