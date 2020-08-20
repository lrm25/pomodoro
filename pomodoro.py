import time

def main():
    print("Enter seconds: ", end='')
    seconds = input()
    for idx in range(int(seconds), 0, -1):
        print(idx)
        time.sleep(1)
    print("Time's up!")

if __name__ == "__main__":
    main()
