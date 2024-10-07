package com.pillgood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pillgood.entity.Category;
import com.pillgood.entity.Item;
import com.pillgood.entity.ItemImage;
import com.pillgood.entity.User;
import com.pillgood.entity.Category.Symptom;
import com.pillgood.entity.Category.Type;
import com.pillgood.repository.CategoryRepository;
import com.pillgood.repository.ItemRepository;
import com.pillgood.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InsertService {
	@Autowired
	ItemRepository itemRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder brCryptPasswordEncoder;
	
	
	public void insertAdminUser() {
		User adminUser = User.builder()
				.email("admin@admin.com")
				.password(brCryptPasswordEncoder.encode("admin"))
				.name("admin")
				.provider("HOME")
				.role("ADMIN")
				.build();
		
		userRepository.save(adminUser);
	}
	
	public void insertCategory() {
		
		for(int i=0;i<Category.Type.values().length;i++) {
			for(int j=0;j<Category.Symptom.values().length;j++) {
				Category category = Category.builder()
						.symptom(Category.Symptom.values()[j])
						.type(Category.Type.values()[i])
						.build(); 
				categoryRepository.save(category);
			}
		}
		
		
	}
	
	public void insertItem() {
		
		Item item1 = Item.builder()
				.brand("스업윔")
				.name("스업윔")
				.price(36000L)
				.description("남성에게 필요한 멀티비타민 미네랄의 황금밸런스, 항산화 작용을 하는 라이코펜/비타민ACE 함유, 임신준비에 필요한 아연과 엽산 적정량 함유")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.남성건강, Category.Type.건강기능식품))
				.build();
		item1.getItemImageList().add(ItemImage.builder().img("스업윔1").url("https://img.pilly.kr/product/store/PDMASNLS3733/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item1).build());
		item1.getItemImageList().add(ItemImage.builder().img("스업윔2").url("https://img.pilly.kr/product/store/PDMASNLS3733/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item1).build());
		item1.getItemImageList().add(ItemImage.builder().img("스업윔3").url("https://img.pilly.kr/product/store/PDMASNLS3733/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item1).build());
		item1.getItemImageList().add(ItemImage.builder().img("스업윔설명1").url("https://img.pilly.kr/product/store/PDMASNLS3733/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item1).build());
		item1.getItemImageList().add(ItemImage.builder().img("스업윔설명2").url("https://img.pilly.kr/product/store/PDMASNLS3733/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item1).build());
		item1.getItemImageList().add(ItemImage.builder().img("스업윔설명3").url("https://img.pilly.kr/product/store/PDMASNLS3733/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item1).build());
		item1.getItemImageList().add(ItemImage.builder().img("스업윔설명4").url("https://img.pilly.kr/product/store/PDMASNLS3733/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item1).build());
		item1.getItemImageList().add(ItemImage.builder().img("스업윔설명5").url("https://img.pilly.kr/product/store/PDMASNLS3733/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item1).build());
		item1.getItemImageList().add(ItemImage.builder().img("스업윔설명6").url("https://img.pilly.kr/product/store/PDMASNLS3733/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item1).build());
		item1.getItemImageList().add(ItemImage.builder().img("스업윔설명7").url("https://img.pilly.kr/product/store/PDMASNLS3733/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item1).build());
		item1.getItemImageList().add(ItemImage.builder().img("스업윔설명8").url("https://img.pilly.kr/product/store/PDMASNLS3733/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item1).build());
		item1.getItemImageList().add(ItemImage.builder().img("스업윔설명9").url("https://img.pilly.kr/product/store/PDMASNLS3733/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item1).build());
		
		Item item2 = Item.builder()
				.brand("나트롤")
				.name("나트롤 비오틴 10,000 에프디 60정")
				.price(14900L)
				.description("하루를 채우는 달콤한 33,300% 충전 비오틴, 하루 한알 츄어블 타입으로 쉽고 간편하게 섭취 가능한 비오틴")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.모발, Category.Type.건강기능식품))
				.build();
		item2.getItemImageList().add(ItemImage.builder().img("나트롤1").url("https://img.pilly.kr/product/store/PDKIMLRD6444/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item2).build());
		item2.getItemImageList().add(ItemImage.builder().img("나트롤2").url("https://img.pilly.kr/product/store/PDKIMLRD6444/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item2).build());
		item2.getItemImageList().add(ItemImage.builder().img("나트롤설명1").url("https://img.pilly.kr/product/store/PDKIMLRD6444/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item2).build());
		item2.getItemImageList().add(ItemImage.builder().img("나트롤설명2").url("https://img.pilly.kr/product/store/PDKIMLRD6444/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item2).build());
		item2.getItemImageList().add(ItemImage.builder().img("나트롤설명3").url("https://img.pilly.kr/product/store/PDKIMLRD6444/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item2).build());
		item2.getItemImageList().add(ItemImage.builder().img("나트롤설명4").url("https://img.pilly.kr/product/store/PDKIMLRD6444/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item2).build());
		item2.getItemImageList().add(ItemImage.builder().img("나트롤설명5").url("https://img.pilly.kr/product/store/PDKIMLRD6444/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item2).build());
		item2.getItemImageList().add(ItemImage.builder().img("나트롤설명6").url("https://img.pilly.kr/product/store/PDKIMLRD6444/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item2).build());
		item2.getItemImageList().add(ItemImage.builder().img("나트롤설명7").url("https://img.pilly.kr/product/store/PDKIMLRD6444/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item2).build());
		item2.getItemImageList().add(ItemImage.builder().img("나트롤설명8").url("https://img.pilly.kr/product/store/PDKIMLRD6444/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item2).build());
		item2.getItemImageList().add(ItemImage.builder().img("나트롤설명9").url("https://img.pilly.kr/product/store/PDKIMLRD6444/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item2).build());
		item2.getItemImageList().add(ItemImage.builder().img("나트롤설명10").url("https://img.pilly.kr/product/store/PDKIMLRD6444/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item2).build());
		
		Item item3 = Item.builder()
				.brand("자로우 포뮬라스")
				.name("자로우 우먼즈 펨 도필러스 30캡슐")
				.price(27900L)
				.description("글로벌 no.1 질유산균 (매년 북미 소비자 조사 1위). 비건캡슐, 알러지프리 1.5cm의 미니사이즈. 세계 3대 유산균 기업 한센의 urex 프로바이오틱스를 사용.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.여성건강, Category.Type.건강기능식품))
				.build();
		item3.getItemImageList().add(ItemImage.builder().img("자로우1").url("https://img.pilly.kr/product/store/PDCTNIBI6699/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우1").url("https://img.pilly.kr/product/store/PDCTNIBI6699/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명1").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명2").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명3").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명4").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명5").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명6").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명7").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명8").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명9").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명10").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명11").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_11.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명12").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_12.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명13").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_13.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명14").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_14.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명15").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_15.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명16").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_16.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명17").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_17.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명18").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_18.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
		item3.getItemImageList().add(ItemImage.builder().img("자로우설명19").url("https://img.pilly.kr/product/store/PDCTNIBI6699/detail_19.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item3).build());
	
		Item item4 = Item.builder()
				.brand("필리")
				.name("루테인")
				.price(10600L)
				.description("필리 루테인은 인도 카르나타카에서 재배된 마리골드꽃추출물을 사용하고 어두운 곳에서 시각 적응을 위해 필요한 비타민A를 포함하여 우수한 품질관리를 통해 만들었습니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.눈, Category.Type.건강기능식품))
				.build();
		item4.getItemImageList().add(ItemImage.builder().img("루테인1").url("https://img.pilly.kr/product/store/PDVCBZUX4669/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item4).build());
		item4.getItemImageList().add(ItemImage.builder().img("루테인2").url("https://img.pilly.kr/product/store/PDVCBZUX4669/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item4).build());
		item4.getItemImageList().add(ItemImage.builder().img("루테인설명1").url("https://img.pilly.kr/product/store/PDVCBZUX4669/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item4).build());
		
		Item item5 = Item.builder()
				.brand("필리")
				.name("밀크씨슬")
				.price(11300L)
				.description("필리 밀크씨슬은 유럽산 밀크씨슬을 이용하고 우수한 품질관리를 통해 만들었습니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.면역력, Category.Type.건강기능식품))
				.build();
		item5.getItemImageList().add(ItemImage.builder().img("밀크씨슬1").url("https://img.pilly.kr/product/store/PDPMO159KRV/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item5).build());
		item5.getItemImageList().add(ItemImage.builder().img("밀크씨슬2").url("https://img.pilly.kr/product/store/PDPMO159KRV/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item5).build());
		item5.getItemImageList().add(ItemImage.builder().img("밀크씨슬설명1").url("https://img.pilly.kr/product/store/PDPMO159KRV/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item5).build());
		item5.getItemImageList().add(ItemImage.builder().img("밀크씨슬설명2").url("https://img.pilly.kr/product/store/PDPMO159KRV/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item5).build());
		item5.getItemImageList().add(ItemImage.builder().img("밀크씨슬설명3").url("https://img.pilly.kr/product/store/PDPMO159KRV/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item5).build());
		
		Item item6 = Item.builder()
				.brand("필리")
				.name("홍삼 옥타코사놀")
				.price(21000L)
				.description("필리 홍삼 옥타코사놀은 국내산 6년근 홍삼과 옥타코사놀에 천연 비타민E를 포함하여 우수한 품질관리를 통해 만들었습니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.면역력, Category.Type.건강기능식품))
				.build();
		item6.getItemImageList().add(ItemImage.builder().img("홍삼 옥타코사놀1").url("https://img.pilly.kr/product/store/PDIZYTWB7736/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item6).build());
		item6.getItemImageList().add(ItemImage.builder().img("홍삼 옥타코사놀2").url("https://img.pilly.kr/product/store/PDIZYTWB7736/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item6).build());
		item6.getItemImageList().add(ItemImage.builder().img("홍삼 옥타코사놀설명1").url("https://img.pilly.kr/product/store/PDIZYTWB7736/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item6).build());
		item6.getItemImageList().add(ItemImage.builder().img("홍삼 옥타코사놀설명2").url("https://img.pilly.kr/product/store/PDIZYTWB7736/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item6).build());
		item6.getItemImageList().add(ItemImage.builder().img("홍삼 옥타코사놀설명3").url("https://img.pilly.kr/product/store/PDIZYTWB7736/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item6).build());
		
		Item item7 = Item.builder()
				.brand("필리")
				.name("비타민C")
				.price(11900L)
				.description("필리 비타민C는 품질이 좋은 영국 DSM사의 비타민C 원료와 셀레늄, 아연을 포함하여 우수한 품질관리를 통해 만들었습니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.피로, Category.Type.건강기능식품))
				.build();
		item7.getItemImageList().add(ItemImage.builder().img("비타민C1").url("https://img.pilly.kr/product/store/PD0I8DW3HS9N/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item7).build());
		item7.getItemImageList().add(ItemImage.builder().img("비타민C1").url("https://img.pilly.kr/product/store/PD0I8DW3HS9N/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item7).build());
		item7.getItemImageList().add(ItemImage.builder().img("비타민C설명1").url("https://img.pilly.kr/product/store/PD0I8DW3HS9N/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item7).build());
		item7.getItemImageList().add(ItemImage.builder().img("비타민C설명2").url("https://img.pilly.kr/product/store/PD0I8DW3HS9N/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item7).build());
		item7.getItemImageList().add(ItemImage.builder().img("비타민C설명3").url("https://img.pilly.kr/product/store/PD0I8DW3HS9N/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item7).build());
		
		Item item8 = Item.builder()
				.brand("필리")
				.name("칼슘 마그네슘 비타민D")
				.price(12400L)
				.description("필리 칼슘 마그네슘 비타민D는 북대서양 해저에서 수확한 해조류를 사용한 영국산 해조분말에 비타민D와 마그네슘을 포함하여 우수한 품질관리를 통해 만들었습니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.관절, Category.Type.건강기능식품))
				.build();
		item8.getItemImageList().add(ItemImage.builder().img("비타민D1").url("https://img.pilly.kr/product/store/PDX5OMRMKQNS/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item8).build());
		item8.getItemImageList().add(ItemImage.builder().img("비타민D2").url("https://img.pilly.kr/product/store/PDX5OMRMKQNS/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item8).build());
		item8.getItemImageList().add(ItemImage.builder().img("비타민D설명1").url("https://img.pilly.kr/product/store/PDX5OMRMKQNS/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item8).build());
		item8.getItemImageList().add(ItemImage.builder().img("비타민D설명2").url("https://img.pilly.kr/product/store/PDX5OMRMKQNS/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item8).build());
		item8.getItemImageList().add(ItemImage.builder().img("비타민D설명3").url("https://img.pilly.kr/product/store/PDX5OMRMKQNS/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item8).build());
		
		Item item9 = Item.builder()
				.brand("필리")
				.name("오메가3")
				.price(13500L)
				.description("필리 오메가3는 작은 어류를 원료로 사용하는 노르웨이산 프리미엄 rTG 오메가3(EPA 및 DHA 함유유지)를 사용하고 우수한 품질관리를 통해 만들었습니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.혈관, Category.Type.건강기능식품))
				.build();
		item9.getItemImageList().add(ItemImage.builder().img("오메가31").url("https://img.pilly.kr/product/store/PDC6K049PCFQ/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item9).build());
		item9.getItemImageList().add(ItemImage.builder().img("오메가32").url("https://img.pilly.kr/product/store/PDC6K049PCFQ/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item9).build());
		item9.getItemImageList().add(ItemImage.builder().img("오메가3설명1").url("https://img.pilly.kr/product/store/PDC6K049PCFQ/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item9).build());
		item9.getItemImageList().add(ItemImage.builder().img("오메가3설명2").url("https://img.pilly.kr/product/store/PDC6K049PCFQ/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item9).build());
		item9.getItemImageList().add(ItemImage.builder().img("오메가3설명3").url("https://img.pilly.kr/product/store/PDC6K049PCFQ/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item9).build());
		
		Item item10 = Item.builder()
				.brand("필리")
				.name("프로바이오틱스")
				.price(13800L)
				.description("필리 프로바이오틱스는 캐나다 로셀의 PROBIOCAPⓇ 특허원료와 미국 다니스코 원료를 포함한 11종의 유산균을 사용하여 우수한 품질관리를 통해 만들었습니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.장, Category.Type.건강기능식품))
				.build();
		item10.getItemImageList().add(ItemImage.builder().img("프로바이오틱스1").url("https://img.pilly.kr/product/store/PDJDOMQS7443/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item10).build());
		item10.getItemImageList().add(ItemImage.builder().img("프로바이오틱스1").url("https://img.pilly.kr/product/store/PDJDOMQS7443/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item10).build());
		item10.getItemImageList().add(ItemImage.builder().img("프로바이오틱스설명1").url("https://img.pilly.kr/product/store/PDJDOMQS7443/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item10).build());
		item10.getItemImageList().add(ItemImage.builder().img("프로바이오틱스설명2").url("https://img.pilly.kr/product/store/PDJDOMQS7443/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item10).build());
		item10.getItemImageList().add(ItemImage.builder().img("프로바이오틱스설명3").url("https://img.pilly.kr/product/store/PDJDOMQS7443/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item10).build());
		
		Item item11 = Item.builder()
				.brand("필리")
				.name("비타민B")
				.price(12000L)
				.description("필리 비타민B는 7가지 주요 비타민B군의 비타민을 충분히 섭취할 수 있고 우수한 품질관리를 통해 만들었습니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.면역력, Category.Type.건강기능식품))
				.build();
		item11.getItemImageList().add(ItemImage.builder().img("비타민B1").url("https://img.pilly.kr/product/store/PDZBA94HTD2Y/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item11).build());
		item11.getItemImageList().add(ItemImage.builder().img("비타민B2").url("https://img.pilly.kr/product/store/PDZBA94HTD2Y/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item11).build());
		item11.getItemImageList().add(ItemImage.builder().img("비타민B설명1").url("https://img.pilly.kr/product/store/PDZBA94HTD2Y/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item11).build());
		item11.getItemImageList().add(ItemImage.builder().img("비타민B설명2").url("https://img.pilly.kr/product/store/PDZBA94HTD2Y/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item11).build());
		item11.getItemImageList().add(ItemImage.builder().img("비타민B설명3").url("https://img.pilly.kr/product/store/PDZBA94HTD2Y/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item11).build());
		
		Item item12 = Item.builder()
				.brand("필리")
				.name("철분 츄어블")
				.price(13000L)
				.description("누구나 쉽게 씹어먹을 수 있는 새콤달콤한 맛의 연질캡슐 제형 철분제로, 철분의 흡수와 작용에 도움을 주는 비타민B12, 비타민C를 함께 담았습니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.피로, Category.Type.건강기능식품))
				.build();
		item12.getItemImageList().add(ItemImage.builder().img("철분1").url("https://img.pilly.kr/product/store/PDJILGYX7766/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item12).build());
		item12.getItemImageList().add(ItemImage.builder().img("철분설명1").url("https://img.pilly.kr/product/store/PDJILGYX7766/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item12).build());
		item12.getItemImageList().add(ItemImage.builder().img("철분설명2").url("https://img.pilly.kr/product/store/PDJILGYX7766/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item12).build());
		item12.getItemImageList().add(ItemImage.builder().img("철분설명3").url("https://img.pilly.kr/product/store/PDJILGYX7766/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item12).build());
		
		Item item13 = Item.builder()
				.brand("필리")
				.name("테아닌")
				.price(13000L)
				.description("누구나 쉽게 씹어먹을 수 있는 새콤달콤한 맛의 연질캡슐 제형 철분제로, 철분의 흡수와 작용에 도움을 주는 비타민B12, 비타민C를 함께 담았습니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.면역력, Category.Type.건강기능식품))
				.build();
		item13.getItemImageList().add(ItemImage.builder().img("테아닌1").url("https://img.pilly.kr/product/store/PDYLJOQO9667/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item13).build());
		item13.getItemImageList().add(ItemImage.builder().img("테아닌설명1").url("https://img.pilly.kr/product/store/PDYLJOQO9667/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item13).build());
		item13.getItemImageList().add(ItemImage.builder().img("테아닌설명2").url("https://img.pilly.kr/product/store/PDYLJOQO9667/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item13).build());
		item13.getItemImageList().add(ItemImage.builder().img("테아닌설명3").url("https://img.pilly.kr/product/store/PDYLJOQO9667/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item13).build());
		item13.getItemImageList().add(ItemImage.builder().img("테아닌설명4").url("https://img.pilly.kr/product/store/PDYLJOQO9667/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item13).build());
		item13.getItemImageList().add(ItemImage.builder().img("테아닌설명5").url("https://img.pilly.kr/product/store/PDYLJOQO9667/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item13).build());
		
		Item item14 = Item.builder()
				.brand("온누리스토어")
				.name("프레스샷")
				.price(25700L)
				.description("어디서나 간편하게 섭취 가능하고 흡수가 빠른 액상 제형의 11중 기능성 올인원 영양 앰플")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.피부, Category.Type.건강기능식품))
				.build();
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷1").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷2").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/product_02.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷3").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷4").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷5").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명1").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명2").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_02.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명3").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명4").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_04.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명5").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명6").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명7").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명8").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_08.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명9").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명10").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명11").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_11.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명12").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_12.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명13").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_13.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명14").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_14.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명15").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_15.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		item14.getItemImageList().add(ItemImage.builder().img("프레스샷설명16").url("https://img.pilly.kr/product/store/PDPTAVCZ7443/detail_16.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item14).build());
		
		Item item15 = Item.builder()
				.brand("PHEW")
				.name("이뮨밸런스 : 롤러코스터 하차 선언")
				.price(19500L)
				.description("비타민D, 아연을 비롯해 여성 건강에 도움이 되는 베리 5종까지 7종을 배합해 만든 구미 제형의 건강기능식품으로 월경으로 인한 불편감 완화에 도움이 될 수 있습니다")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.여성건강, Category.Type.건강기능식품))
				.build();
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터1").url("https://img.pilly.kr/product/store/PDUBUWYD7693/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터2").url("https://img.pilly.kr/product/store/PDUBUWYD7693/product_02.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터3").url("https://img.pilly.kr/product/store/PDUBUWYD7693/product_03.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터4").url("https://img.pilly.kr/product/store/PDUBUWYD7693/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터5").url("https://img.pilly.kr/product/store/PDUBUWYD7693/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터6").url("https://img.pilly.kr/product/store/PDUBUWYD7693/product_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명1").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명2").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명3").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명4").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명5").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명6").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명7").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명8").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명9").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명10").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명11").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_11.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명12").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_12.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명13").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_13.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명14").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_14.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명15").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_15.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명16").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_16.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		item15.getItemImageList().add(ItemImage.builder().img("롤러코스터설명17").url("https://img.pilly.kr/product/store/PDUBUWYD7693/detail_17.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item15).build());
		
		Item item16 = Item.builder()
				.brand("PHEW")
				.name("피스&프리 : 그날의 극적 화해")
				.price(32500L)
				.description("타 브랜드 대비 높은 함량 감마리놀렌산(300mg)과 비타민D, 비타민E, 부원료의 특화 배합으로 여성 건강 특화 레시피입니다")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.여성건강, Category.Type.건강기능식품))
				.build();
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해1").url("https://img.pilly.kr/product/store/PDYWPKFW7396/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해2").url("https://img.pilly.kr/product/store/PDYWPKFW7396/product_02.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해3").url("https://img.pilly.kr/product/store/PDYWPKFW7396/product_03.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해4").url("https://img.pilly.kr/product/store/PDYWPKFW7396/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해5").url("https://img.pilly.kr/product/store/PDYWPKFW7396/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해6").url("https://img.pilly.kr/product/store/PDYWPKFW7396/product_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해설명1").url("https://img.pilly.kr/product/store/PDYWPKFW7396/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해설명2").url("https://img.pilly.kr/product/store/PDYWPKFW7396/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해설명3").url("https://img.pilly.kr/product/store/PDYWPKFW7396/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해설명4").url("https://img.pilly.kr/product/store/PDYWPKFW7396/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해설명5").url("https://img.pilly.kr/product/store/PDYWPKFW7396/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해설명6").url("https://img.pilly.kr/product/store/PDYWPKFW7396/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해설명7").url("https://img.pilly.kr/product/store/PDYWPKFW7396/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해설명8").url("https://img.pilly.kr/product/store/PDYWPKFW7396/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해설명9").url("https://img.pilly.kr/product/store/PDYWPKFW7396/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해설명10").url("https://img.pilly.kr/product/store/PDYWPKFW7396/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해설명11").url("https://img.pilly.kr/product/store/PDYWPKFW7396/detail_11.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해설명12").url("https://img.pilly.kr/product/store/PDYWPKFW7396/detail_12.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해설명13").url("https://img.pilly.kr/product/store/PDYWPKFW7396/detail_13.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해설명14").url("https://img.pilly.kr/product/store/PDYWPKFW7396/detail_14.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item16).build());
		item16.getItemImageList().add(ItemImage.builder().img("그날의극적화해설명15").url("https://img.pilly.kr/product/store/PDYWPKFW7396/detail_15.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item16).build());
		
		Item item17 = Item.builder()
				.brand("필리")
				.name("메가 프로폴리스 면역젤리")
				.price(13500L)
				.description("프로폴리스 젤리를 한번 더 업그레이드한 3.0버전으로 플라보노이드 함량은 2.35배 up 아연 2.55mg을 추가해 항산화, 구강항균, 정상면역 삼중 기능성 확보")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.면역력, Category.Type.건강기능식품))
				.build();
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리1").url("https://img.pilly.kr/product/store/PDJWHTYK4379/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리2").url("https://img.pilly.kr/product/store/PDJWHTYK4379/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리3").url("https://img.pilly.kr/product/store/PDJWHTYK4379/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명1").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_101.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명2").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_102.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명3").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_103.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명4").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_104.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명5").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_105.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명6").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_106.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명7").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_107.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명8").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_108.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명9").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_109.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명10").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_110.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명11").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_111.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명12").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_112.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명13").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_113.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명14").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_114.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명15").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_115.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명16").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_116.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명17").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_117.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명18").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_118.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명19").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_119.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명20").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_120.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명21").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_121.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		item17.getItemImageList().add(ItemImage.builder().img("면역젤리설명22").url("https://img.pilly.kr/product/store/PDJWHTYK4379/detail_122.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item17).build());
		
		Item item18 = Item.builder()
				.brand("골리")
				.name("골리 애플사이다 비니거 구미")
				.price(23900L)
				.description("글루텐, 젤라틴 및 동물성원료를 사용하지 않은 제품으로 깐깐한 기준(미국FDA, 코셔인증 및 비건인증)을 통과한 비건간식입니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.피로, Category.Type.건강식품))				
				.build();
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미1").url("https://img.pilly.kr/product/store/PDMENAOT3943/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미2").url("https://img.pilly.kr/product/store/PDMENAOT3943/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미3").url("https://img.pilly.kr/product/store/PDMENAOT3943/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미4").url("https://img.pilly.kr/product/store/PDMENAOT3943/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미5").url("https://img.pilly.kr/product/store/PDMENAOT3943/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미6").url("https://img.pilly.kr/product/store/PDMENAOT3943/product_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미7").url("https://img.pilly.kr/product/store/PDMENAOT3943/product_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미8").url("https://img.pilly.kr/product/store/PDMENAOT3943/product_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명1").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명2").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명3").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명4").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명5").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명6").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명7").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명8").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명9").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명10").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명11").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_11.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명12").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_12.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명13").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_13.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명14").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_14.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명15").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_15.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명16").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_16.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명17").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_17.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명18").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_18.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명19").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_19.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		item18.getItemImageList().add(ItemImage.builder().img("애플사이다비니거구미설명20").url("https://img.pilly.kr/product/store/PDMENAOT3943/detail_20.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item18).build());
		
		Item item19 = Item.builder()
				.brand("마카롱 EX")
				.name("마카롱EX 15포")
				.price(24800L)
				.description("고함량 마카 3,000mg 함유, 간편한 섭취로 언제 어디서나 맛있게 섭취, L-아르기닌과 아연으로 더 강하게")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.남성건강, Category.Type.건강식품))				
				.build();
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX1").url("https://img.pilly.kr/product/store/PDAOSWHU3799/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX2").url("https://img.pilly.kr/product/store/PDAOSWHU3799/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명1").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명2").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명3").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명4").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명5").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명6").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명7").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_07.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명8").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명9").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명10").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_10.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명11").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_11.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명12").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_12.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명13").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_13.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명14").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_14.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명15").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_15.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명16").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_16.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명17").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_17.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명18").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_18.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명19").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_19.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명20").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_20.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명21").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_21.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명22").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_22.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		item19.getItemImageList().add(ItemImage.builder().img("마카롱EX설명23").url("https://img.pilly.kr/product/store/PDAOSWHU3799/detail_23.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item19).build());
		
		Item item20 = Item.builder()
				.brand("체크오")
				.name("체크오 아르타민")
				.price(34900L)
				.description("아르기닌, 비타민B, 비타민C를 포함하는 물에 타 먹는 자몽맛 에너지 분말입니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.피로, Category.Type.건강식품))				
				.build();
		item20.getItemImageList().add(ItemImage.builder().img("아르타민1").url("https://img.pilly.kr/product/store/PDMUNHUE4647/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item20).build());
		item20.getItemImageList().add(ItemImage.builder().img("아르타민2").url("https://img.pilly.kr/product/store/PDMUNHUE4647/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item20).build());
		item20.getItemImageList().add(ItemImage.builder().img("아르타민3").url("https://img.pilly.kr/product/store/PDMUNHUE4647/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item20).build());
		item20.getItemImageList().add(ItemImage.builder().img("아르타민4").url("https://img.pilly.kr/product/store/PDMUNHUE4647/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item20).build());
		item20.getItemImageList().add(ItemImage.builder().img("아르타민설명1").url("https://img.pilly.kr/product/store/PDMUNHUE4647/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item20).build());
		item20.getItemImageList().add(ItemImage.builder().img("아르타민설명2").url("https://img.pilly.kr/product/store/PDMUNHUE4647/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item20).build());
		item20.getItemImageList().add(ItemImage.builder().img("아르타민설명3").url("https://img.pilly.kr/product/store/PDMUNHUE4647/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item20).build());
		item20.getItemImageList().add(ItemImage.builder().img("아르타민설명4").url("https://img.pilly.kr/product/store/PDMUNHUE4647/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item20).build());
		item20.getItemImageList().add(ItemImage.builder().img("아르타민설명5").url("https://img.pilly.kr/product/store/PDMUNHUE4647/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item20).build());
		
		Item item21 = Item.builder()
				.brand("필리")
				.name("자연발효 소화효소 2000000")
				.price(21600L)
				.description("한국인 식습관에 맞춘 고역가 4종 효소로 만들어 역가 대비 가장 뛰어난 가성비를 구현했을 뿐 아니라, 미숫가루 먹듯 맛있게 먹을 수 있는 효소입니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.장, Category.Type.건강식품))				
				.build();
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소1").url("https://img.pilly.kr/product/store/PDODZMKQ3364/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소2").url("https://img.pilly.kr/product/store/PDODZMKQ3364/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소3").url("https://img.pilly.kr/product/store/PDODZMKQ3364/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명1").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_201.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명2").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_202.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명3").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_203.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명4").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_204.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명5").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_205.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명6").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_206.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명7").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_207.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명8").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_208.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명9").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_209.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명10").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_210.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명11").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_211.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명12").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_212.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명13").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_213.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명14").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_214.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명15").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_215.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명16").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_216.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명17").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_217.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명18").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_218.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명19").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_219.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명20").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_220.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명21").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_221.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		item21.getItemImageList().add(ItemImage.builder().img("자연발효소화효소설명22").url("https://img.pilly.kr/product/store/PDODZMKQ3364/detail_222.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item21).build());
		
		Item item22 = Item.builder()
				.brand("만만한프로젝트")
				.name("알이즈웰 만만해지는 젤리")
				.price(22500L)
				.description("알이즈웰은 천연 재료인 프로폴리스를 베이스로 플라보노이드 40mg 함량을 채운 상품입니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.면역력, Category.Type.건강식품))				
				.build();
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리1").url("https://img.pilly.kr/product/store/PDYVJILI4634/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리2").url("https://img.pilly.kr/product/store/PDYVJILI4634/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명1").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_37.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명2").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_38.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명3").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_39.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명4").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_40.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명5").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_41.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명6").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_42.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명7").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_43.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명8").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_44.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명9").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_45.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명10").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_46.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명11").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_47.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명12").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_48.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명13").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_49.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명14").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_50.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명15").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_51.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명16").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_52.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명17").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_53.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명18").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_54.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명19").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_55.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명20").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_56.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명21").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_57.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명22").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_58.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명23").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_59.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명24").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_60.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		item22.getItemImageList().add(ItemImage.builder().img("만만해지는젤리설명25").url("https://img.pilly.kr/product/store/PDYVJILI4634/detail_61.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item22).build());
		
		Item item23 = Item.builder()
				.brand("PHEW")
				.name("P콰이엇 : 거침없이 이별 통보")
				.price(29500L)
				.description("여성 건강에 도움이 되는 13종 원료를 과학적으로 배합하고, 고함량 영양성분을 간편하게 맛있게 섭취할 수 있는 레시피로 복합적인 월경 불편감을 완화하는데 도움이 될 수 있습니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.여성건강, Category.Type.건강식품))				
				.build();
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보1").url("https://img.pilly.kr/product/store/PDUJLDJI6776/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보2").url("https://img.pilly.kr/product/store/PDUJLDJI6776/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보3").url("https://img.pilly.kr/product/store/PDUJLDJI6776/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보4").url("https://img.pilly.kr/product/store/PDUJLDJI6776/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명1").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명2").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명3").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명4").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명5").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명6").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명7").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명8").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명9").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명10").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명11").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_11.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명12").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_12.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명13").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_13.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명14").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_14.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명15").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_15.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명16").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_16.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명17").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_17.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		item23.getItemImageList().add(ItemImage.builder().img("거침없이이별통보설명18").url("https://img.pilly.kr/product/store/PDUJLDJI6776/detail_18.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item23).build());
		
		Item item24 = Item.builder()
				.brand("글로썸")
				.name("홀리스모크 팔로산토 버너")
				.price(65000L)
				.description("팔로산토 스머지 스틱을 태우는 그릇 혹은 인테리어 소품으로 취향과 용도에 맞게 활용해 보세요.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.마음건강, Category.Type.건강용품))				
				.build();
		item24.getItemImageList().add(ItemImage.builder().img("팔로산토버너1").url("https://img.pilly.kr/product/store/PDHZADOL6673/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item24).build());
		item24.getItemImageList().add(ItemImage.builder().img("팔로산토버너2").url("https://img.pilly.kr/product/store/PDHZADOL6673/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item24).build());
		item24.getItemImageList().add(ItemImage.builder().img("팔로산토버너3").url("https://img.pilly.kr/product/store/PDHZADOL6673/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item24).build());
		item24.getItemImageList().add(ItemImage.builder().img("팔로산토버너4").url("https://img.pilly.kr/product/store/PDHZADOL6673/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item24).build());
		item24.getItemImageList().add(ItemImage.builder().img("팔로산토버너5").url("https://img.pilly.kr/product/store/PDHZADOL6673/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item24).build());
		item24.getItemImageList().add(ItemImage.builder().img("팔로산토버너설명1").url("https://img.pilly.kr/product/store/PDHZADOL6673/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item24).build());
		item24.getItemImageList().add(ItemImage.builder().img("팔로산토버너설명2").url("https://img.pilly.kr/product/store/PDHZADOL6673/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item24).build());
		item24.getItemImageList().add(ItemImage.builder().img("팔로산토버너설명3").url("https://img.pilly.kr/product/store/PDHZADOL6673/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item24).build());
		item24.getItemImageList().add(ItemImage.builder().img("팔로산토버너설명4").url("https://img.pilly.kr/product/store/PDHZADOL6673/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item24).build());
		
		Item item25 = Item.builder()
				.brand("글로썸")
				.name("홀리스모크 팔로산토(THIN)")
				.price(8900L)
				.description("시트러스함을 지닌 스모키한 우드향에 이어, 은은한 달콤함이 코 끝을 스치는 팔로산토. 나무 본연의 신비로운 향이 부정적인 기운을 정화하고 행운을 불러온다고 전해져 내려오는 100% 천연 향입니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.마음건강, Category.Type.건강용품))				
				.build();
		item25.getItemImageList().add(ItemImage.builder().img("팔로산토(THIN)1").url("https://img.pilly.kr/product/store/PDPMPPDX3736/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item25).build());
		item25.getItemImageList().add(ItemImage.builder().img("팔로산토(THIN)2").url("https://img.pilly.kr/product/store/PDPMPPDX3736/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item25).build());
		item25.getItemImageList().add(ItemImage.builder().img("팔로산토(THIN)3").url("https://img.pilly.kr/product/store/PDPMPPDX3736/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item25).build());
		item25.getItemImageList().add(ItemImage.builder().img("팔로산토(THIN)4").url("https://img.pilly.kr/product/store/PDPMPPDX3736/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item25).build());
		item25.getItemImageList().add(ItemImage.builder().img("팔로산토(THIN)설명1").url("https://img.pilly.kr/product/store/PDPMPPDX3736/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item25).build());
		item25.getItemImageList().add(ItemImage.builder().img("팔로산토(THIN)설명2").url("https://img.pilly.kr/product/store/PDPMPPDX3736/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item25).build());
		item25.getItemImageList().add(ItemImage.builder().img("팔로산토(THIN)설명3").url("https://img.pilly.kr/product/store/PDPMPPDX3736/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item25).build());
		item25.getItemImageList().add(ItemImage.builder().img("팔로산토(THIN)설명4").url("https://img.pilly.kr/product/store/PDPMPPDX3736/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item25).build());
		item25.getItemImageList().add(ItemImage.builder().img("팔로산토(THIN)설명5").url("https://img.pilly.kr/product/store/PDPMPPDX3736/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item25).build());
		item25.getItemImageList().add(ItemImage.builder().img("팔로산토(THIN)설명6").url("https://img.pilly.kr/product/store/PDPMPPDX3736/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item25).build());
		
		Item item26 = Item.builder()
				.brand("글로썸")
				.name("홀리스모크 인센스 홀더")
				.price(33000L)
				.description("인센스 스틱을 태우는 그릇 혹은 인테리어 소품으로 취향과 용도에 맞게 활용해 보세요. 고유한 무드로 공간에 마음에 차분함을 전합니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.마음건강, Category.Type.건강용품))				
				.build();
		item26.getItemImageList().add(ItemImage.builder().img("인센스 홀더1").url("https://img.pilly.kr/product/store/PDASNJIM6378/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item26).build());
		item26.getItemImageList().add(ItemImage.builder().img("인센스 홀더2").url("https://img.pilly.kr/product/store/PDASNJIM6378/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item26).build());
		item26.getItemImageList().add(ItemImage.builder().img("인센스 홀더3").url("https://img.pilly.kr/product/store/PDASNJIM6378/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item26).build());
		item26.getItemImageList().add(ItemImage.builder().img("인센스 홀더4").url("https://img.pilly.kr/product/store/PDASNJIM6378/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item26).build());
		item26.getItemImageList().add(ItemImage.builder().img("인센스 홀더5").url("https://img.pilly.kr/product/store/PDASNJIM6378/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item26).build());
		item26.getItemImageList().add(ItemImage.builder().img("인센스 홀더설명1").url("https://img.pilly.kr/product/store/PDASNJIM6378/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item26).build());
		item26.getItemImageList().add(ItemImage.builder().img("인센스 홀더설명2").url("https://img.pilly.kr/product/store/PDASNJIM6378/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item26).build());
		item26.getItemImageList().add(ItemImage.builder().img("인센스 홀더설명3").url("https://img.pilly.kr/product/store/PDASNJIM6378/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item26).build());
		item26.getItemImageList().add(ItemImage.builder().img("인센스 홀더설명4").url("https://img.pilly.kr/product/store/PDASNJIM6378/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item26).build());
		item26.getItemImageList().add(ItemImage.builder().img("인센스 홀더설명5").url("https://img.pilly.kr/product/store/PDASNJIM6378/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item26).build());
		
		Item item27 = Item.builder()
				.brand("테라브레스")
				.name("프레시브레스 오랄린스 아이시민트 473ML")
				.price(11900L)
				.description("테라브레스 프레시브레스 아이시민트는 미국 테라브레스 본사에서 한국 식약처 기준에 맞춰 직접 제작한 공식수입제품입니다. 구강 내 프라그 및 악취제거는 물론 자일리톨과 아이시민트의 청량감을 더했습니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.구강관리, Category.Type.건강용품))				
				.build();
		item27.getItemImageList().add(ItemImage.builder().img("오랄린스 아이시민트1").url("https://img.pilly.kr/product/store/PDCUSGCT6339/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item27).build());
		item27.getItemImageList().add(ItemImage.builder().img("오랄린스 아이시민트2").url("https://img.pilly.kr/product/store/PDCUSGCT6339/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item27).build());
		item27.getItemImageList().add(ItemImage.builder().img("오랄린스 아이시민트설명1").url("https://img.pilly.kr/product/store/PDCUSGCT6339/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item27).build());
		item27.getItemImageList().add(ItemImage.builder().img("오랄린스 아이시민트설명2").url("https://img.pilly.kr/product/store/PDCUSGCT6339/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item27).build());
		item27.getItemImageList().add(ItemImage.builder().img("오랄린스 아이시민트설명3").url("https://img.pilly.kr/product/store/PDCUSGCT6339/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item27).build());
		item27.getItemImageList().add(ItemImage.builder().img("오랄린스 아이시민트설명4").url("https://img.pilly.kr/product/store/PDCUSGCT6339/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item27).build());
		item27.getItemImageList().add(ItemImage.builder().img("오랄린스 아이시민트설명5").url("https://img.pilly.kr/product/store/PDCUSGCT6339/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item27).build());
		item27.getItemImageList().add(ItemImage.builder().img("오랄린스 아이시민트설명6").url("https://img.pilly.kr/product/store/PDCUSGCT6339/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item27).build());
		item27.getItemImageList().add(ItemImage.builder().img("오랄린스 아이시민트설명7").url("https://img.pilly.kr/product/store/PDCUSGCT6339/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item27).build());
		
		Item item28 = Item.builder()
				.brand("테라브레스")
				.name("프레시브레스 오랄린스 마일드민트 473ML")
				.price(11900L)
				.description("테라브레스 프레시브레스 마일드민트는 미국 테라브레스 본사에서 한국 식약처 기준에 맞춰 직접 제작한 공식수입제품입니다. 자극적이지않고 순한 사용감으로 저자극으로 입냄새를 없애고 싶은 분들께 추천합니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.구강관리, Category.Type.건강용품))				
				.build();
		item28.getItemImageList().add(ItemImage.builder().img("오랄린스 마일드민트1").url("https://img.pilly.kr/product/store/PDQEXZEZ4933/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item28).build());
		item28.getItemImageList().add(ItemImage.builder().img("오랄린스 마일드민트2").url("https://img.pilly.kr/product/store/PDQEXZEZ4933/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item28).build());
		item28.getItemImageList().add(ItemImage.builder().img("오랄린스 마일드민트설명1").url("https://img.pilly.kr/product/store/PDQEXZEZ4933/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item28).build());
		item28.getItemImageList().add(ItemImage.builder().img("오랄린스 마일드민트설명2").url("https://img.pilly.kr/product/store/PDQEXZEZ4933/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item28).build());
		item28.getItemImageList().add(ItemImage.builder().img("오랄린스 마일드민트설명3").url("https://img.pilly.kr/product/store/PDQEXZEZ4933/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item28).build());
		item28.getItemImageList().add(ItemImage.builder().img("오랄린스 마일드민트설명4").url("https://img.pilly.kr/product/store/PDQEXZEZ4933/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item28).build());
		item28.getItemImageList().add(ItemImage.builder().img("오랄린스 마일드민트설명5").url("https://img.pilly.kr/product/store/PDQEXZEZ4933/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item28).build());
		item28.getItemImageList().add(ItemImage.builder().img("오랄린스 마일드민트설명6").url("https://img.pilly.kr/product/store/PDQEXZEZ4933/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item28).build());
		item28.getItemImageList().add(ItemImage.builder().img("오랄린스 마일드민트설명7").url("https://img.pilly.kr/product/store/PDQEXZEZ4933/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item28).build());
		
		Item item29 = Item.builder()
				.brand("닥터노아")
				.name("오비츠 자일리톨 고체치약 유자민트향")
				.price(5300L)
				.description("자일리톨 함량은 높이고 유해성분은 낮춘 오비츠 자일리톨 고체치약입니다. 부드러운 유자민트향을 느껴보세요.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.구강관리, Category.Type.건강용품))				
				.build();
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향1").url("https://img.pilly.kr/product/store/PDHVICPP3333/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item29).build());
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향2").url("https://img.pilly.kr/product/store/PDHVICPP3333/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item29).build());
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향3").url("https://img.pilly.kr/product/store/PDHVICPP3333/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item29).build());
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향4").url("https://img.pilly.kr/product/store/PDHVICPP3333/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item29).build());
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향5").url("https://img.pilly.kr/product/store/PDHVICPP3333/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item29).build());
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향설명1").url("https://img.pilly.kr/product/store/PDHVICPP3333/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item29).build());
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향설명2").url("https://img.pilly.kr/product/store/PDHVICPP3333/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item29).build());
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향설명3").url("https://img.pilly.kr/product/store/PDHVICPP3333/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item29).build());
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향설명4").url("https://img.pilly.kr/product/store/PDHVICPP3333/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item29).build());
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향설명5").url("https://img.pilly.kr/product/store/PDHVICPP3333/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item29).build());
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향설명6").url("https://img.pilly.kr/product/store/PDHVICPP3333/detail_06.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item29).build());
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향설명7").url("https://img.pilly.kr/product/store/PDHVICPP3333/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item29).build());
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향설명8").url("https://img.pilly.kr/product/store/PDHVICPP3333/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item29).build());
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향설명9").url("https://img.pilly.kr/product/store/PDHVICPP3333/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item29).build());
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향설명10").url("https://img.pilly.kr/product/store/PDHVICPP3333/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item29).build());
		item29.getItemImageList().add(ItemImage.builder().img("고체치약 유자민트향설명11").url("https://img.pilly.kr/product/store/PDHVICPP3333/detail_11.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item29).build());
		
		Item item30 = Item.builder()
				.brand("닥터노아")
				.name("오비츠 자일리톨 고체치약 프레쉬민트향")
				.price(5300L)
				.description("자일리톨 함량은 높이고 유해성분은 낮춘 오비츠 자일리톨 고체치약입니다. 상쾌한 프레쉬민트향을 느껴보세요.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.구강관리, Category.Type.건강용품))				
				.build();
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향1").url("https://img.pilly.kr/product/store/PDBUVVOI3999/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item30).build());
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향2").url("https://img.pilly.kr/product/store/PDBUVVOI3999/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item30).build());
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향3").url("https://img.pilly.kr/product/store/PDBUVVOI3999/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item30).build());
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향4").url("https://img.pilly.kr/product/store/PDBUVVOI3999/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item30).build());
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향5").url("https://img.pilly.kr/product/store/PDBUVVOI3999/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item30).build());
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향설명1").url("https://img.pilly.kr/product/store/PDBUVVOI3999/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item30).build());
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향설명2").url("https://img.pilly.kr/product/store/PDBUVVOI3999/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item30).build());
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향설명3").url("https://img.pilly.kr/product/store/PDBUVVOI3999/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item30).build());
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향설명4").url("https://img.pilly.kr/product/store/PDBUVVOI3999/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item30).build());
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향설명5").url("https://img.pilly.kr/product/store/PDBUVVOI3999/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item30).build());
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향설명6").url("https://img.pilly.kr/product/store/PDBUVVOI3999/detail_06.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item30).build());
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향설명7").url("https://img.pilly.kr/product/store/PDBUVVOI3999/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item30).build());
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향설명8").url("https://img.pilly.kr/product/store/PDBUVVOI3999/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item30).build());
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향설명9").url("https://img.pilly.kr/product/store/PDBUVVOI3999/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item30).build());
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향설명10").url("https://img.pilly.kr/product/store/PDBUVVOI3999/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item30).build());
		item30.getItemImageList().add(ItemImage.builder().img("고체치약 프레쉬민트향설명11").url("https://img.pilly.kr/product/store/PDBUVVOI3999/detail_11.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item30).build());
		
		Item item31 = Item.builder()
				.brand("닥터노아")
				.name("마루 대나무 칫솔 스탠다드(6개입)")
				.price(15960L)
				.description("이중미세모로 치석 제거와 잇몸 케어 두 가지를 동시에 케어하는 마루 대나무 칫솔입니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.구강관리, Category.Type.건강용품))				
				.build();
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)1").url("https://img.pilly.kr/product/store/PDQSTPRO9693/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)2").url("https://img.pilly.kr/product/store/PDQSTPRO9693/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)3").url("https://img.pilly.kr/product/store/PDQSTPRO9693/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)4").url("https://img.pilly.kr/product/store/PDQSTPRO9693/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)5").url("https://img.pilly.kr/product/store/PDQSTPRO9693/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)6").url("https://img.pilly.kr/product/store/PDQSTPRO9693/product_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)7").url("https://img.pilly.kr/product/store/PDQSTPRO9693/product_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)설명1").url("https://img.pilly.kr/product/store/PDQSTPRO9693/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)설명2").url("https://img.pilly.kr/product/store/PDQSTPRO9693/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)설명3").url("https://img.pilly.kr/product/store/PDQSTPRO9693/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)설명4").url("https://img.pilly.kr/product/store/PDQSTPRO9693/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)설명5").url("https://img.pilly.kr/product/store/PDQSTPRO9693/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)설명6").url("https://img.pilly.kr/product/store/PDQSTPRO9693/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)설명7").url("https://img.pilly.kr/product/store/PDQSTPRO9693/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)설명8").url("https://img.pilly.kr/product/store/PDQSTPRO9693/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)설명9").url("https://img.pilly.kr/product/store/PDQSTPRO9693/detail_09.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)설명10").url("https://img.pilly.kr/product/store/PDQSTPRO9693/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)설명11").url("https://img.pilly.kr/product/store/PDQSTPRO9693/detail_11.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)설명12").url("https://img.pilly.kr/product/store/PDQSTPRO9693/detail_12.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item31).build());
		item31.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(6개입)설명13").url("https://img.pilly.kr/product/store/PDQSTPRO9693/detail_13.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item31).build());
		
		Item item32 = Item.builder()
				.brand("닥터노아")
				.name("마루 대나무 칫솔 스탠다드(1개입)")
				.price(2800L)
				.description("이중미세모로 치석 제거와 잇몸 케어 두 가지를 동시에 케어하는 마루 대나무 칫솔입니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.구강관리, Category.Type.건강용품))				
				.build();
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)1").url("https://img.pilly.kr/product/store/PDQOWRSM4644/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)2").url("https://img.pilly.kr/product/store/PDQOWRSM4644/product_02.jpg?v=v202405101214?v=v20240510	1214").type(ItemImage.Type.상품).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)3").url("https://img.pilly.kr/product/store/PDQOWRSM4644/product_03.jpg?v=v202405101214?v=v20240510	1214").type(ItemImage.Type.상품).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)4").url("https://img.pilly.kr/product/store/PDQOWRSM4644/product_04.jpg?v=v202405101214?v=v20240510	1214").type(ItemImage.Type.상품).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)설명1").url("https://img.pilly.kr/product/store/PDQOWRSM4644/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)설명2").url("https://img.pilly.kr/product/store/PDQOWRSM4644/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)설명3").url("https://img.pilly.kr/product/store/PDQOWRSM4644/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)설명4").url("https://img.pilly.kr/product/store/PDQOWRSM4644/detail_04.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)설명5").url("https://img.pilly.kr/product/store/PDQOWRSM4644/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)설명6").url("https://img.pilly.kr/product/store/PDQOWRSM4644/detail_06.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)설명7").url("https://img.pilly.kr/product/store/PDQOWRSM4644/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)설명8").url("https://img.pilly.kr/product/store/PDQOWRSM4644/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)설명9").url("https://img.pilly.kr/product/store/PDQOWRSM4644/detail_09.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)설명10").url("https://img.pilly.kr/product/store/PDQOWRSM4644/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)설명11").url("https://img.pilly.kr/product/store/PDQOWRSM4644/detail_11.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)설명12").url("https://img.pilly.kr/product/store/PDQOWRSM4644/detail_12.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item32).build());
		item32.getItemImageList().add(ItemImage.builder().img("마루 대나무 칫솔 스탠다드(1개입)설명13").url("https://img.pilly.kr/product/store/PDQOWRSM4644/detail_13.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item32).build());
		
		Item item33 = Item.builder()
				.brand("바디픽셀")
				.name("캐리밴드 종아리 압박밴드 S")
				.price(10700L)
				.description("바디픽셀 캐리밴드 종아리압박밴드는 100% 국내에서 생산한 자체 제작 1등급 의료기기 압박밴드S(스몰) 입니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.관절, Category.Type.건강용품))				
				.build();
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S1").url("https://img.pilly.kr/product/store/PDOOGMTE7343/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S2").url("https://img.pilly.kr/product/store/PDOOGMTE7343/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S3").url("https://img.pilly.kr/product/store/PDOOGMTE7343/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S4").url("https://img.pilly.kr/product/store/PDOOGMTE7343/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S5").url("https://img.pilly.kr/product/store/PDOOGMTE7343/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S6").url("https://img.pilly.kr/product/store/PDOOGMTE7343/product_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S설명1").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_01.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S설명2").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S설명3").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S설명4").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S설명5").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S설명6").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_06.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S설명7").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S설명8").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S설명9").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S설명10").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S설명11").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_11.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item33).build());
		item33.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 S설명12").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_12.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item33).build());
		
		Item item34 = Item.builder()
				.brand("바디픽셀")
				.name("캐리밴드 종아리 압박밴드 M")
				.price(10700L)
				.description("바디픽셀 캐리밴드 종아리압박밴드는 100% 국내에서 생산한 자체 제작 1등급 의료기기 압박밴드M(미디움) 입니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.관절, Category.Type.건강용품))				
				.build();
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M1").url("https://img.pilly.kr/product/store/PDOOGMTE7343/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M2").url("https://img.pilly.kr/product/store/PDOOGMTE7343/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M3").url("https://img.pilly.kr/product/store/PDOOGMTE7343/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M4").url("https://img.pilly.kr/product/store/PDOOGMTE7343/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M5").url("https://img.pilly.kr/product/store/PDOOGMTE7343/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M6").url("https://img.pilly.kr/product/store/PDOOGMTE7343/product_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M설명1").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_01.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M설명2").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M설명3").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M설명4").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M설명5").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M설명6").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_06.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M설명7").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M설명8").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M설명9").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M설명10").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M설명11").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_11.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item34).build());
		item34.getItemImageList().add(ItemImage.builder().img("캐리밴드 종아리 압박밴드 M설명12").url("https://img.pilly.kr/product/store/PDOOGMTE7343/detail_12.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item34).build());
		
		Item item35 = Item.builder()
				.brand("바디픽셀")
				.name("캐리밴드 무릎보호대 M(블랙)")
				.price(7900L)
				.description("의료기기로 등록된 무릎용 압박밴드 밸런스엑스밴드M(미디움)입니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.관절, Category.Type.건강용품))				
				.build();
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)1").url("https://img.pilly.kr/product/store/PDFAMJKY6633/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item35).build());
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)2").url("https://img.pilly.kr/product/store/PDFAMJKY6633/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item35).build());
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)3").url("https://img.pilly.kr/product/store/PDFAMJKY6633/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item35).build());
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)4").url("https://img.pilly.kr/product/store/PDFAMJKY6633/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item35).build());
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)5").url("https://img.pilly.kr/product/store/PDFAMJKY6633/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item35).build());
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)6").url("https://img.pilly.kr/product/store/PDFAMJKY6633/product_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item35).build());
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)설명1").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item35).build());
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)설명2").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item35).build());
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)설명3").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item35).build());
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)설명4").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item35).build());
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)설명5").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item35).build());
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)설명6").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item35).build());
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)설명7").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item35).build());
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)설명8").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item35).build());
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)설명9").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item35).build());
		item35.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 M(블랙)설명10").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item35).build());
		
		Item item36 = Item.builder()
				.brand("바디픽셀")
				.name("캐리밴드 무릎보호대 L(블랙)")
				.price(7900L)
				.description("의료기기로 등록된 무릎용 압박밴드 밸런스엑스밴드L(라지)입니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.관절, Category.Type.건강용품))				
				.build();
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)1").url("https://img.pilly.kr/product/store/PDFAMJKY6633/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item36).build());
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)2").url("https://img.pilly.kr/product/store/PDFAMJKY6633/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item36).build());
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)3").url("https://img.pilly.kr/product/store/PDFAMJKY6633/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item36).build());
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)4").url("https://img.pilly.kr/product/store/PDFAMJKY6633/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item36).build());
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)5").url("https://img.pilly.kr/product/store/PDFAMJKY6633/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item36).build());
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)6").url("https://img.pilly.kr/product/store/PDFAMJKY6633/product_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item36).build());
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)설명1").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item36).build());
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)설명2").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item36).build());
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)설명3").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item36).build());
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)설명4").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item36).build());
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)설명5").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item36).build());
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)설명6").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item36).build());
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)설명7").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item36).build());
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)설명8").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item36).build());
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)설명9").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item36).build());
		item36.getItemImageList().add(ItemImage.builder().img("캐리밴드 무릎보호대 L(블랙)설명10").url("https://img.pilly.kr/product/store/PDFAMJKY6633/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item36).build());
		
		Item item37 = Item.builder()
				.brand("바디픽셀")
				.name("캐리밴드 손목보호대 R(오른손)")
				.price(10100L)
				.description("압박강도 조절로 간편하게 일상 손목 보호를 할 수 있는 손목보호대(오른손)입니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.관절, Category.Type.건강용품))				
				.build();
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)1").url("https://img.pilly.kr/product/store/PDFODBUT6339/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)2").url("https://img.pilly.kr/product/store/PDFODBUT6339/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)3").url("https://img.pilly.kr/product/store/PDFODBUT6339/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)4").url("https://img.pilly.kr/product/store/PDFODBUT6339/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)5").url("https://img.pilly.kr/product/store/PDFODBUT6339/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)6").url("https://img.pilly.kr/product/store/PDFODBUT6339/product_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명1").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명2").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_02.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명3").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명4").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명5").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명6").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명7").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명8").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명9").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명10").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명11").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_11.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명12").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_12.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명13").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_13.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명14").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_14.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명15").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_15.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명16").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_16.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명17").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_17.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명18").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_18.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명19").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_19.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명20").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_20.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명21").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_21.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명22").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_22.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명23").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_23.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명24").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_24.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명25").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_25.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명26").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_26.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명27").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_27.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명28").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_28.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명29").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_29.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명30").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_30.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명31").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_31.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명32").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_32.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		item37.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명33").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_33.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item37).build());
		
		Item item38 = Item.builder()
				.brand("바디픽셀")
				.name("캐리밴드 손목보호대 L(왼손)")
				.price(10100L)
				.description("압박강도 조절로 간편하게 일상 손목 보호를 할 수 있는 손목보호대(왼손)입니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.관절, Category.Type.건강용품))				
				.build();
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)1").url("https://img.pilly.kr/product/store/PDFODBUT6339/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)2").url("https://img.pilly.kr/product/store/PDFODBUT6339/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)3").url("https://img.pilly.kr/product/store/PDFODBUT6339/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)4").url("https://img.pilly.kr/product/store/PDFODBUT6339/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)5").url("https://img.pilly.kr/product/store/PDFODBUT6339/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)6").url("https://img.pilly.kr/product/store/PDFODBUT6339/product_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명1").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명2").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_02.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명3").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명4").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명5").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명6").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명7").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명8").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명9").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명10").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명11").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_11.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명12").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_12.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명13").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_13.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명14").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_14.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명15").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_15.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명16").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_16.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명17").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_17.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명18").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_18.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명19").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_19.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명20").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_20.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명21").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_21.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명22").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_22.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명23").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_23.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명24").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_24.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명25").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_25.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명26").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_26.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명27").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_27.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명28").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_28.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명29").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_29.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명30").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_30.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명31").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_31.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명32").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_32.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
		item38.getItemImageList().add(ItemImage.builder().img("캐리밴드 손목보호대 R(오른손)설명33").url("https://img.pilly.kr/product/store/PDFODBUT6339/detail_33.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item38).build());
	
		Item item39 = Item.builder()
				.brand("바디픽셀")
				.name("바르다 목선생 목교정기")
				.price(11900L)
				.description("우리 목을 위한 바른자세 목 서포터 바르다 목교정기입니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.관절, Category.Type.건강용품))				
				.build();
		item39.getItemImageList().add(ItemImage.builder().img("목교정기1").url("https://img.pilly.kr/product/store/PDAYQJXN7677/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기2").url("https://img.pilly.kr/product/store/PDAYQJXN7677/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기3").url("https://img.pilly.kr/product/store/PDAYQJXN7677/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기4").url("https://img.pilly.kr/product/store/PDAYQJXN7677/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기5").url("https://img.pilly.kr/product/store/PDAYQJXN7677/product_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기6").url("https://img.pilly.kr/product/store/PDAYQJXN7677/product_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기설명1").url("https://img.pilly.kr/product/store/PDAYQJXN7677/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기설명2").url("https://img.pilly.kr/product/store/PDAYQJXN7677/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기설명3").url("https://img.pilly.kr/product/store/PDAYQJXN7677/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기설명4").url("https://img.pilly.kr/product/store/PDAYQJXN7677/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기설명5").url("https://img.pilly.kr/product/store/PDAYQJXN7677/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기설명6").url("https://img.pilly.kr/product/store/PDAYQJXN7677/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기설명7").url("https://img.pilly.kr/product/store/PDAYQJXN7677/detail_07.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기설명8").url("https://img.pilly.kr/product/store/PDAYQJXN7677/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기설명9").url("https://img.pilly.kr/product/store/PDAYQJXN7677/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기설명10").url("https://img.pilly.kr/product/store/PDAYQJXN7677/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기설명11").url("https://img.pilly.kr/product/store/PDAYQJXN7677/detail_11.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기설명12").url("https://img.pilly.kr/product/store/PDAYQJXN7677/detail_12.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기설명13").url("https://img.pilly.kr/product/store/PDAYQJXN7677/detail_13.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기설명14").url("https://img.pilly.kr/product/store/PDAYQJXN7677/detail_14.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item39).build());
		item39.getItemImageList().add(ItemImage.builder().img("목교정기설명15").url("https://img.pilly.kr/product/store/PDAYQJXN7677/detail_15.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item39).build());
		
		Item item40 = Item.builder()
				.brand("필리")
				.name("내츄럴 비타민 21.5% 인핸싱 시트 마스크")
				.price(2500L)
				.description("필리가 선택한 비타민나무수, 보성어린녹차수의 비타민 컴플렉스가 21.5% 함유된 비타민 마스크팩으로 피부부터 수분과 비타민을 가득 채워줍니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.피부, Category.Type.건강용품))				
				.build();
		item40.getItemImageList().add(ItemImage.builder().img("내츄럴 비타민 21.5% 인핸싱 시트 마스크1").url("https://img.pilly.kr/product/store/PDIVFLQX7439/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item40).build());
		item40.getItemImageList().add(ItemImage.builder().img("내츄럴 비타민 21.5% 인핸싱 시트 마스크2").url("https://img.pilly.kr/product/store/PDIVFLQX7439/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item40).build());
		item40.getItemImageList().add(ItemImage.builder().img("내츄럴 비타민 21.5% 인핸싱 시트 마스크설명1").url("https://img.pilly.kr/product/store/PDIVFLQX7439/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item40).build());
		item40.getItemImageList().add(ItemImage.builder().img("내츄럴 비타민 21.5% 인핸싱 시트 마스크설명2").url("https://img.pilly.kr/product/store/PDIVFLQX7439/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item40).build());
		item40.getItemImageList().add(ItemImage.builder().img("내츄럴 비타민 21.5% 인핸싱 시트 마스크설명3").url("https://img.pilly.kr/product/store/PDIVFLQX7439/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item40).build());
		
		Item item41 = Item.builder()
				.brand("필리")
				.name("헬시사이클링 고래빗")
				.price(2500L)
				.description("자원의 순환과 지속 가능한 환경을 위한 필리 헬시얼스 챌린지의 일환으로 제작한 필리만의 새활용 굿즈로, 고객님들이 직접 보내주신 영양제 용기를 활용해 만든 100% 재활용 플라스틱 제품입니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.모발, Category.Type.건강용품))				
				.build();
		item41.getItemImageList().add(ItemImage.builder().img("헬시사이클링 고래빗1").url("https://img.pilly.kr/product/store/PDCFUIFC9393/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item41).build());
		item41.getItemImageList().add(ItemImage.builder().img("헬시사이클링 고래빗2").url("https://img.pilly.kr/product/store/PDCFUIFC9393/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item41).build());
		item41.getItemImageList().add(ItemImage.builder().img("헬시사이클링 고래빗3").url("https://img.pilly.kr/product/store/PDCFUIFC9393/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item41).build());
		item41.getItemImageList().add(ItemImage.builder().img("헬시사이클링 고래빗설명1").url("https://img.pilly.kr/product/store/PDCFUIFC9393/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item41).build());
		item41.getItemImageList().add(ItemImage.builder().img("헬시사이클링 고래빗설명2").url("https://img.pilly.kr/product/store/PDCFUIFC9393/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item41).build());
		item41.getItemImageList().add(ItemImage.builder().img("헬시사이클링 고래빗설명3").url("https://img.pilly.kr/product/store/PDCFUIFC9393/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item41).build());
		item41.getItemImageList().add(ItemImage.builder().img("헬시사이클링 고래빗설명4").url("https://img.pilly.kr/product/store/PDCFUIFC9393/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item41).build());
		item41.getItemImageList().add(ItemImage.builder().img("헬시사이클링 고래빗설명5").url("https://img.pilly.kr/product/store/PDCFUIFC9393/detail_05.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item41).build());
		
		Item item42 = Item.builder()
				.brand("필리")
				.name("생리 5일 전 붙이는 아랫배 따순팩")
				.price(5900L)
				.description("배를 충분히 덮는 사이즈와 몸 굴곡에 맞는 형태의 아랫배 전용 온열팩으로, 체온을 올려 생리통을 완화하는데 도움을 줍니다.")
				.category(categoryRepository.findBySymptomAndType(Category.Symptom.여성건강, Category.Type.건강용품))				
				.build();
		item42.getItemImageList().add(ItemImage.builder().img("따순팩1").url("https://img.pilly.kr/product/store/PDPPEDFN9477/product_01@3x.png?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩2").url("https://img.pilly.kr/product/store/PDPPEDFN9477/product_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩3").url("https://img.pilly.kr/product/store/PDPPEDFN9477/product_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩4").url("https://img.pilly.kr/product/store/PDPPEDFN9477/product_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명1").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_01.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명2").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_02.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명3").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_03.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명4").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_04.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명5").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_05.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명6").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_06.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명7").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_07.gif?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명8").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_08.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명9").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_09.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명10").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_10.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명11").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_11.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명12").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_12.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명13").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_13.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명14").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_14.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명15").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_15.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명16").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_16.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명17").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_17.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명18").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_18.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명19").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_19.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명20").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_20.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명21").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_21.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명22").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_22.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		item42.getItemImageList().add(ItemImage.builder().img("따순팩설명23").url("https://img.pilly.kr/product/store/PDPPEDFN9477/detail_23.jpg?v=v202405101214?v=v202405101214").type(ItemImage.Type.상품설명).item(item42).build());
		
		itemRepository.save(item1);
		itemRepository.save(item2);
		itemRepository.save(item3);
		itemRepository.save(item4);
		itemRepository.save(item5);
		itemRepository.save(item6);
		itemRepository.save(item7);
		itemRepository.save(item8);
		itemRepository.save(item9);
		itemRepository.save(item10);
		itemRepository.save(item11);
		itemRepository.save(item12);
		itemRepository.save(item13);
		itemRepository.save(item14);
		itemRepository.save(item15);
		itemRepository.save(item16);
		itemRepository.save(item17);
		itemRepository.save(item18);
		itemRepository.save(item19);
		itemRepository.save(item20);
		itemRepository.save(item21);
		itemRepository.save(item22);
		itemRepository.save(item23);
		itemRepository.save(item24);
		itemRepository.save(item25);
		itemRepository.save(item26);
		itemRepository.save(item27);
		itemRepository.save(item28);
		itemRepository.save(item29);
		itemRepository.save(item30);
		itemRepository.save(item31);
		itemRepository.save(item32);
		itemRepository.save(item33);
		itemRepository.save(item34);
		itemRepository.save(item35);
		itemRepository.save(item36);
		itemRepository.save(item37);
		itemRepository.save(item38);
		itemRepository.save(item39);
		itemRepository.save(item40);
		itemRepository.save(item41);
		itemRepository.save(item42);
		
	}
}
