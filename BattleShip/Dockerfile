# Use an official Java development kit as the parent image
FROM openjdk:11

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy the current directory contents into the container at /usr/src/app
COPY . .

# Compile the Java application
RUN javac Main.java Board.java Coordinate.java Ship.java

# Run the Java application when the container launches
CMD ["java", "Main"]
