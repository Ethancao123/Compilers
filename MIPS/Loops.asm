# 
#   Counts numbers between a low and high value with a certain step in between
#   @author Ethan Cao
#   @version 11/10/21
#

    .data
        promptLow: .asciiz "Input the low end value \n"
        promptHigh: .asciiz "Input the high end value \n"
        promptStep: .asciiz "Input the step value \n"
        newLine: .asciiz "\n"
    .text 0x00400000
    .globl main
    
main:
    jal prompt
loop:
    #adds step to the current value and prints
    addu $t0, $t0, $t2
    move $a0, $t0
    li $v0, 1
    syscall

    #prints a new line
    li $v0, 4
    la $a0, newLine
    syscall

    #continue to loop if the current value is less than high
    bgt $t1, $t0, loop
end:

    #end of program
    li $v0, 10
    syscall

prompt:
    #asks the user the input the low end
    li $v0, 4
    la $a0, promptLow
    syscall
    li $v0, 5
    syscall
    move $t0, $v0 #low is in t0, high is in t1, step is in t2
    
    #asks the user to input the high end
    li $v0, 4
    la $a0, promptHigh
    syscall
    li $v0, 5
    syscall
    move $t1, $v0

    #asks the user to input the step
    li $v0, 4
    la $a0, promptStep
    syscall
    li $v0, 5
    syscall
    move $t2, $v0
    jr $ra
    