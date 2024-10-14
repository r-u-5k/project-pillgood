import { useEffect, useRef } from 'react';
import flatpickr from 'flatpickr';
import 'flatpickr/dist/flatpickr.min.css';

const DatePicker = ({ onSelect }) => {
    
  const inputRef = useRef(null);

  useEffect(() => {
    const datePicker = flatpickr(inputRef.current, {
      dateFormat: 'Y-m-d', // 날짜 형식 지정
      maxDate: 'today', // 오늘 이전 날짜 선택 불가능하게 설정
      onChange: onSelect, // 선택한 날짜를 전달할 콜백 함수
    });

    return () => {
      datePicker.destroy(); // 컴포넌트가 언마운트될 때 Flatpickr 인스턴스 제거
    };
  }, [onSelect]);

  return <input ref={inputRef} type="text" className='bg-white w-full h-full border-[1px] pl-[15px] rounded-xl border-gray-900 text-gray-900 placeholder:text-gray-900 inline' placeholder="날짜 선택" />;
};

export default DatePicker;