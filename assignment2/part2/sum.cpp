#include <iostream>
#include <memory>
using namespace std;

int main() {
    int* arr = new int[5]{1,2,3,4,5};
    int sum = 0; for (int i = 0; i < 5; ++i) sum += arr[i];
    cout << "sum = " << sum << "\n";
    delete[] arr;

    auto buf = std::make_unique<int[]>(5);
    for (int i = 0; i < 5; ++i) buf[i] = i;
    cout << "last = " << buf[4] << "\n";
}