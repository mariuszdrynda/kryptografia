class GLIBC:
    def __init__(self, seed=1):
        self.seed = seed
        self.states = [seed]
        for i in range(1, 31):
            self.states.append((16807 * self.states[i - 1]) % (2 ** 31 - 1))
        for i in range(31, 34):
            self.states.append(self.states[i - 31])
        for i in range(34, 345):
            self.states.append((self.states[i - 31] + self.states[i - 3]) % (2 ** 32))
    def random(self):
        new_rand = (self.states[-3] + self.states[-31]) % (2 ** 32)
        self.states.append(new_rand)
        return new_rand >> 1

def predict_new_value(numbers):
    if len(numbers) < 31:
        print('ERROR: za mało liczb!')
        exit(-1)
    return (numbers[-31] + numbers[-3]) % 2 ** 31

seed = 1

glibc = GLIBC(seed)
numbers = []
for i in range(344, 375):
    numbers.append(glibc.random())
if_predicted = []
 
for k in range(10000):
    predicted_number = predict_new_value(numbers)
    new_number = glibc.random()
    numbers.append(new_number)
    # gdybyśmy tak napisali, to zawsze zgadywalibyśmy:
    # if_predicted.append(new_number == predicted_number or new_number == predicted_number + 1)
    if_predicted.append(new_number == predicted_number)
print('Prawdopodobieństwo poprawnego zgadnięcia kolejnej liczby wynosi: ', sum(if_predicted)/len(if_predicted))