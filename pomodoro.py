import time

def main():
    for idx in range(10, 0, -1):
        print(idx)
        time.sleep(1)
    print("Time's up!")

if __name__ == "__main__":
    main()
