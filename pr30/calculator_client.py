import requests

def display_result(operation, a, b, result):
    print(f"\n{'='*50}")
    print(f"Operation: {operation} between {a} and {b}")
    print(f"Result: {result}")
    print(f"{'='*50}\n")

def add(a, b):
    response = requests.get(f'http://127.0.0.1:5000/add', params={'a': a, 'b': b})
    display_result("Addition", a, b, response.text)

def subtract(a, b):
    response = requests.get(f'http://127.0.0.1:5000/subtract', params={'a': a, 'b': b})
    display_result("Subtraction", a, b, response.text)

def multiply(a, b):
    response = requests.get(f'http://127.0.0.1:5000/multiply', params={'a': a, 'b': b})
    display_result("Multiplication", a, b, response.text)

def divide(a, b):
    response = requests.get(f'http://127.0.0.1:5000/divide', params={'a': a, 'b': b})
    display_result("Division", a, b, response.text)

# Example usage
a = 10
b = 5

add(a, b)
subtract(a, b)
multiply(a, b)
divide(a, b)

